$(function () {
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
        var url = jypath + '/jc/jcuserstatis/toAdd';
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
                JY.Ajax.doRequest(null, jypath + '/jc/jcuserstatis/deleteBatch', {chks: chks.toString()}, function (data) {
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
function getbaseList(init) {
    if (init == 1)$("#baseForm .pageNum").val(1);
    JY.Model.loading();
    JY.Ajax.doRequest("baseForm", jypath + '/jc/jcuserstatis/findByPage', null, function (data) {
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
                html+="<td class='center'><label> <input type='checkbox' name='ids' value='"+l.issue+"-"+l.userId+"' class='ace' /> <span class='lbl'></span></label></td>";
                html+="<td class='center'>"+(i+leng+1)+"</td>";
                html+="<td class='center'>"+JY.Object.notEmpty(l.mobile)+"</td>";
                html+="<td class='center'>"+JY.Object.notEmpty(l.nick_name)+"</td>";
                html+="<td class='center'>"+JY.Object.notEmpty(l.strongPoint)+"</td>";
                html+="<td class='center'>"+JY.Object.notEmpty(l.totalProfitRate)+"</td>";
                html+="<td class='center'>"+JY.Object.notEmpty(l.totalWinCnt)+"</td>";
                html+="<td class='center'>"+JY.Object.notEmpty(l.totalLostCnt)+"</td>";
                html+="<td class='center'>"+JY.Object.notEmpty(l.myBuyCnt)+"</td>";
                html+="<td class='center'>"+JY.Object.notEmpty(l.myBetCnt)+"</td>";
                html+="<td class='center'>"+JY.Object.notEmpty(l.highestLevel)+"</td>";
                html+="<td class='center'>"+JY.Object.notEmpty(l.totalConsumption)+"</td>";
                html+="<td class='center'>"+JY.Object.notEmpty(l.totalRecharge)+"</td>";
                html+="<td class='center'>"+JY.Object.notEmpty(l.totalConversion)+"</td>";
                html+="<td class='center'>"+JY.Date.Default(l.createTime)+"</td>";
                html+="<td class='center'>"+JY.Date.Default(l.updateTime)+"</td>";
/*
                html+=JY.Tags.setFunction(l.issue+"-"+l.userId,permitBtn); //给按钮传主键
*/
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

//导出excel
function exportExcel() {
    var param = "keyWord=" + $("#baseForm input[name='keyWord']").val();
    window.location.href = jypath + '/jc/jcuserstatis/exportExcel?'+param;
}