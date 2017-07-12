$(function () {
    //下拉框
    // JY.Dict.setSelect("selectisValid","isValid",2,"全部");
    //加载表格
    getbaseList();
    //增加回车事件
    $("#baseForm").keydown(function (e) {
        keycode = e.which || e.keyCode;
        if (keycode == 13) {
            search();
        }
    });
    //新加
    $('#addBtn').on('click', function (e) {
        //通知浏览器不要执行与事件关联的默认动作
        e.preventDefault();
        //通知浏览器不要执行与事件关联的默认动作
        e.preventDefault();
        var url = jypath + '/jc/messagecenter/toAdd';
        art.dialog.open(url, {
            title: '新增', width: '90%', height: 500,
            close: function() {
                search();
            }
        });
    });
    //批量删除
    $('#deleteBatchBtn').on('click', function (e) {
        //通知浏览器不要执行与事件关联的默认动作
        e.preventDefault();
        var chks = [];
        $('#baseTable input[name="ids"]:checked').each(function () {
            chks.push($(this).val());
        });
        if (chks.length == 0) {
            JY.Model.info("您没有选择任何内容!");
        } else {
            JY.Model.confirm("确认要删除选中的数据吗?", function () {
                JY.Ajax.doRequest(null, jypath + '/jc/messagecenter/deleteBatch', {chks: chks.toString()}, function (data) {
                    JY.Model.info(data.resMsg, function () {
                        search();
                    });
                });
            });
        }
    });
});
function search() {
    $("#searchBtn").trigger("click");
}
//类别（1私信（系统消息）, 2系统通知,3卖单推送,4发单推送,5中奖通知,6每日彩蛋），除了1其他的都要推送
function getMsgTypeName(msgType){
    if(msgType == 1){
        return "私信";
    } else if(msgType == 2) {
        return "系统通知";
    } else if(msgType == 3) {
        return "卖单推送";
    } else if(msgType == 4) {
        return "发单推送";
    } else if(msgType == 5) {
        return "中奖通知";
    } else if(msgType == 6) {
        return "每日彩蛋";
    }
}


function getbaseList(init) {
    if (init == 1)$("#baseForm .pageNum").val(1);
    JY.Model.loading();
    JY.Ajax.doRequest("baseForm", jypath + '/jc/messagecenter/findByPage', null, function (data) {
        $("#baseTable tbody").empty();
        var obj = data.obj;
        var list = obj.list;
        var results = list.results;
        var permitBtn = obj.permitBtn;
        var pageNum = list.pageNum, pageSize = list.pageSize, totalRecord = list.totalRecord;
        var html = "";
        if (results != null && results.length > 0) {
            var leng = (pageNum - 1) * pageSize;//计算序号
            for (var i = 0; i < results.length; i++) {
                var l=results[i];
                html+="<tr>";
                html+="<td class='center'><label> <input type='checkbox' name='ids' value='"+l.msgId+"' class='ace' /> <span class='lbl'></span></label></td>";
                html+="<td class='center'>"+(i+leng+1)+"</td>";
/*
                html+="<td class='center'>"+JY.Object.notEmpty(l.msgId)+"</td>";
*/
/*
                html+="<td class='center'>"+JY.Object.notEmpty(l.title)+"</td>";
*/
                html+="<td class='center'>"+JY.Object.notEmpty(l.content)+"</td>";
                html+="<td class='center'>"+JY.Date.Default(l.sendTime)+"</td>";
                html+="<td class='center'>"+getMsgTypeName(l.msgType)+"</td>";
                if(l.state==1) html+="<td class='center hidden-480'><span class='label label-sm label-success'>已经发送</span></td>";
                else             html+="<td class='center hidden-480'><span class='label label-sm arrowed-in'>未发送</span></td>";
                html+="<td class='center'>"+JY.Date.Default(l.createTime)+"</td>";
                html+="<td class='center'>"+JY.Date.Default(l.updateTime)+"</td>";
/*
                html+="<td class='center'>"+JY.Object.notEmpty(l.operId)+"</td>";
*/
                html+=JY.Tags.setFunction(l.msgId,permitBtn); //给按钮传主键
                html+="</tr>";
            }
            $("#baseTable tbody").append(html);
            JY.Page.setPage("baseForm", "pageing", pageSize, pageNum, totalRecord, "getbaseList");
        } else {
            html += "<tr><td colspan='10' class='center'>没有相关数据</td></tr>";
            $("#baseTable tbody").append(html);
            $("#pageing ul").empty();//清空分页
        }

        JY.Model.loadingClose();
    });
}

function findBtn(msgId) {
    var url = jypath + '/jc/messagecenter/toAdd?type=find&msgId=' + msgId;
    art.dialog.open(url, {
        title: '查看', width: '90%', height: 550
    });
}

//单条删除
function deleteBtn(msgId) {
    JY.Model.confirm("确认删除吗？", function () {
        JY.Ajax.doRequest(null, jypath + '/jc/messagecenter/delete', {msgId: msgId}, function (data) {
            JY.Model.info(data.resMsg, function () {
                search();
            });
        });
    });
}
//批量删除
function updateBtn(msgId) {
    var url = jypath + '/jc/messagecenter/toAdd?msgId=' + msgId;
    art.dialog.open(url, {
        title: '修改', width: "90%", height: 500,
        close: function() {
            search();
        }
    });
}