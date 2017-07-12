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
        var url = jypath + '/jc/paintedEgg/toAdd';
        art.dialog.open(url, {
            title: '新增', width: '80%', height: '80%',
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
                JY.Ajax.doRequest(null, jypath + '/jc/paintedEgg/deleteBatch', {chks: chks.toString()}, function (data) {
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
    JY.Ajax.doRequest("baseForm", jypath + '/jc/paintedEgg/findByPage', null, function (data) {
        $("#baseTable tbody").empty();
        var obj = data.obj;
        var list = obj.list;
        var results = list.results;
        var permitBtn = obj.permitBtn;
        var pageNum = list.pageNum, pageSize = list.pageSize, totalRecord = list.totalRecord;
        var html = "";
        if (results != null && results.length > 0) {
            var leng = (pageNum - 1) * pageSize;//计算序号
            var st = '';
            for (var i = 0; i < results.length; i++) {
                var l=results[i];
                if(0 == l.state){
          		  st= "未开始";
          		}else if(1 == l.state){
          			 st= "运行中";
          		}else if(2 == l.state){
          			 st= "已揭晓";
          		}else{
          			st = '未知';
          		}
                html+="<tr>";
                html+="<td class='center'><label> <input type='checkbox' name='ids' value='"+l.eggid+"' class='ace' /> <span class='lbl'></span></label></td>";
                html+="<td class='center'>"+(i+leng+1)+"</td>";
                html+="<td class='center'>"+JY.Object.notEmpty(l.eggid)+"</td>";
                html+="<td class='center'>"+JY.Object.notEmpty(l.content)+"</td>";
                html+="<td class='center'>"+JY.Object.notEmpty(l.title)+"</td>";
                html+="<td class='center'>"+JY.Object.notEmpty(l.team_id1)+"-"+JY.Object.notEmpty(l.team_id2)+"</td>";
                html+="<td class='center'>"+st+"</td>";
                html+="<td class='center'>"+JY.Object.notEmpty(l.price)+"</td>";
                html+="<td class='center'>"+JY.Object.notEmpty(l.purchased)+"</td>";
                html+="<td class='center'>"+JY.Object.notEmpty(l.lowerlimit)+"</td>";
                html+="<td class='center'>"+JY.Date.Default(l.createtime)+"</td>";
                if(1 == l.state){
                	html+="<td class='center'><div class='visible-md visible-lg hidden-sm hidden-xs btn-group'><a href='#' title='查看' onclick='findBtn("+l.eggid+")' class='aBtnNoTD btn btn-white btn-xs' >" +
            		"<i class='icon-zoom-in  bigger-140'></i></a><a href='#' title='修改' onclick='updateBtn("+l.eggid+")' class='aBtnNoTD btn btn-white btn-xs' >" +
            		"<i class='icon-edit  bigger-140'></i></a><a href='#' title='删除' onclick='deleteBtn("+l.eggid+")' class='aBtnNoTD btn btn-white btn-xs' ><i class='icon-trash  bigger-140'></i></a>" +
            		"<a href='#' title='补齐机器人' onclick='zdBtn("+l.eggid+")' class='aBtnNoTD btn btn-white btn-xs' ><i class='icon-ok  bigger-140'></i></a>" +
                    "<a href='#' title='设置答案' onclick='setAnsBtn("+l.eggid+")' class='aBtnNoTD btn btn-white btn-xs' ><i class='icon-check  bigger-140'></i></a></div>"+
                     "</td>";
                }else{
                	html+="<td class='center'><div class='visible-md visible-lg hidden-sm hidden-xs btn-group'><a href='#' title='查看' onclick='findBtn("+l.eggid+")' class='aBtnNoTD btn btn-white btn-xs' >" +
            		"<i class='icon-zoom-in  bigger-140'></i></a><a href='#' title='修改' onclick='updateBtn("+l.eggid+")' class='aBtnNoTD btn btn-white btn-xs' >" +
            		"<i class='icon-edit  bigger-140'></i></a><a href='#' title='删除' onclick='deleteBtn("+l.eggid+")' class='aBtnNoTD btn btn-white btn-xs' ><i class='icon-trash  bigger-140'></i></a>" +
            		"</li></ul></div></div></td>";
                }
                html+="</tr>";
            }
            $("#baseTable tbody").append(html);
            JY.Page.setPage("baseForm", "pageing", pageSize, pageNum, totalRecord, "getbaseList");
        } else {
            html += "<tr><td colspan='12' class='center'>没有相关数据</td></tr>";
            $("#baseTable tbody").append(html);
            $("#pageing ul").empty();//清空分页
        }

        JY.Model.loadingClose();
    });
}

function findBtn(eggid) {
    var url = jypath + '/jc/paintedEgg/find?type=find&eggid=' + eggid;
    art.dialog.open(url, {
        title: '查看', width: '950px', height: '80%'
    });
}
//function zdBtn(eggid){
//	  $("#eggId").val(eggid);
//	  var url = jypath + '/jc/user/index?type=select';
//	  art.dialog.open(url, {title: '选择用户', width: '80%', height: '80%'});
//}

//function zdBtn(eggid){
//	 var url = jypath + '/jc/paintedEgg/toWiner?eggid=' + eggid;
//	    art.dialog.open(url, {
//	        title: '指定中奖人', width: '80%', height: '80%'
//	    });
//}

function zdBtn(eggid){
	 JY.Model.confirm("确认补齐机器人？", function () {
	        JY.Ajax.doRequest(null, jypath + '/jc/paintedEgg/robot', {eggid: eggid}, function (data) {
	            JY.Model.info(data.resMsg, function () {
	                search();
	            });
	        });
	    });
}


function setUserId(chks) {
	var id = $("#eggId").val();
	 JY.Model.confirm("确认指定中奖人吗？", function () {
	        JY.Ajax.doRequest(null, jypath + '/jc/paintedEgg/zd', {eggid: id,userId:chks}, function (data) {
	            JY.Model.info(data.resMsg, function () {
	                search();
	            });
	        });
	    });
}


function setAnsBtn(eggid){
	  var url = jypath + '/jc/paintedEgg/setAnswer?eggid='+eggid;
	  art.dialog.open(url, {title: '设置答案',width: '80%', height: '80%'});
}

//单条删除
function deleteBtn(id) {
    JY.Model.confirm("确认删除吗？", function () {
        JY.Ajax.doRequest(null, jypath + '/jc/paintedEgg/delete', {eggid: id}, function (data) {
            JY.Model.info(data.resMsg, function () {
                search();
            });
        });
    });
}
//修改页面
function updateBtn(eggid) {
	 var url = jypath + '/jc/paintedEgg/find?type=update&eggid=' + eggid;
    art.dialog.open(url, {
        title: '修改', width: '950px', height: '80%',
        close: function() {
            search();
        }
    });
    
    
}

//导出excel
function exportExcel() {
    var param = "keyWord=" + $("#baseForm input[name='keyWord']").val();
    window.location.href = jypath + '/jc/paintedEgg/exportExcel?'+param;
}
