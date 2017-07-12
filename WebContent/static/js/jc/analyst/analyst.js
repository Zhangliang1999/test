$(function () {
	//下拉框
	// JY.Dict.setSelect("selectisValid","isValid",2,"全部");
	var dictData = JY.Dict.getData("matchType");
	matchType = dictData['matchType']['items']; //字典
    //加载表格
	getbaseList();
	//增加回车事件
	$("#baseForm").keydown(function(e){
		 keycode = e.which || e.keyCode;
		 if (keycode==13) {
			 search();
		 } 
	});
	//新加
	/*$('#addBtn').on('click', function(e) {
		//通知浏览器不要执行与事件关联的默认动作		
		e.preventDefault();
		cleanForm();	
		loadRoleTree();
		JY.Model.edit("auDiv","新增",function(){
			 if(JY.Validate.form("auForm")){
				 var that =$(this);
				 JY.Ajax.doRequest("auForm",jypath +'/backstage/account/add',null,function(data){
				     that.dialog("close");      
				     JY.Model.info(data.resMsg,function(){search();});
				 });
			 }	
		});
	});*/
	//批量删除
	$('#delBatchBtn').on('click', function(e) {
		//通知浏览器不要执行与事件关联的默认动作		
		e.preventDefault();
		var chks =[];    
		$('#baseTable input[name="ids"]:checked').each(function(){    
			chks.push($(this).val());    
		});     
		if(chks.length==0) {
			JY.Model.info("您没有选择任何内容!"); 
		}else{
			JY.Model.confirm("确认要删除选中的数据吗?",function(){	
				JY.Ajax.doRequest(null,jypath +'/jc/analyst/deleteBatch',{chks:chks.toString()},function(data){
					JY.Model.info(data.resMsg,function(){search();});
				});
			});		
		}		
	});
});
function search(){
	$("#searchBtn").trigger("click");
}
function getbaseList(init){
	if(init==1)$("#baseForm .pageNum").val(1);	
	JY.Model.loading();
	JY.Ajax.doRequest("baseForm",jypath +'/jc/analyst/findByPage',null,function(data){
		 $("#baseTable tbody").empty();
        	 var obj=data.obj;
        	 var list=obj.list;
        	 var results=list.results;
        	 var permitBtn=obj.permitBtn;
         	 var pageNum=list.pageNum,pageSize=list.pageSize,totalRecord=list.totalRecord;
        	 var html="";
    		 if(results!=null&&results.length>0){
        		 var leng=(pageNum-1)*pageSize;//计算序号
        		 for(var i = 0;i<results.length;i++){
            		 var l=results[i];
            		 html+="<tr>";
            		 html+="<td class='center'><label> <input type='checkbox' name='ids' value='"+l.userId+"' class='ace' /> <span class='lbl'></span></label></td>";
            		 html+="<td class='center'>"+(i+leng+1)+"</td>";
            		 html+="<td class='center'>"+JY.Object.notEmpty(l.userId)+"</td>";
            		 html+="<td class='center'>"+JY.Object.notEmpty(l.mobile)+"</td>";
            		 html+="<td class='center'>"+JY.Object.notEmpty(l.nickName)+"</td>";
                     html+="<td class='center'>"+JY.Object.notEmpty(l.realName)+"</td>";
                     html+="<td class='center' >"+JY.Object.notEmpty(l.idCard)+"</td>";
            		 html+="<td class='center'>"+JY.Object.notEmpty(l.experience)+"</td>";
            		 html+="<td class='center'>"+JY.Object.notEmpty(JY.Dict.getName(matchType,l.matchType))+"</td>";
            		 if(l.state==2) html+="<td class='center hidden-480'><span class='label label-sm label-success'>审核通过</span></td>";
            		 else if(l.state==3)            html+="<td class='center hidden-480'><span class='label label-sm arrowed-in'>审核不通过</span></td>";
                     else html+="<td class='center hidden-480'><span class='label label-sm arrowed-in'>待审核</span></td>";
            		 //html+=JY.Tags.setFunction(l.userId+"-"+l.matchType,permitBtn); //给按钮传主键
					 if(l.state==1) {
						 html+="<td class=\"center\"><div class=\"visible-md visible-lg hidden-sm hidden-xs btn-group\">";
						 html+="<a href=\"#\" title=\"审核通过\" onclick=\"doPass('"+l.userId+"', "+l.matchType+")\" class=\"aBtnNoTD btn btn-white btn-xs\"><i class=\"icon-ok color-green bigger-140\"></i></a>";
						 html+="<a href=\"#\" title=\"审核不通过\" onclick=\"doNoPass('"+l.userId+"', "+l.matchType+")\" class=\"aBtnNoTD btn btn-white btn-xs\"><i class=\"icon-remove color-bright-red bigger-140\"></i></a>";
						 html+="</div></td>";
					 } else {
						 html+="<td>&nbsp;</td>";
					 }
            		 html+="</tr>";
            	 } 
        		 $("#baseTable tbody").append(html);
        		 JY.Page.setPage("baseForm","pageing",pageSize,pageNum,totalRecord,"getbaseList");
        	 }else{
        		html+="<tr><td colspan='10' class='center'>没有相关数据</td></tr>";
        		$("#baseTable tbody").append(html);
        		$("#pageing ul").empty();//清空分页
        	 }	
 	 
    	 JY.Model.loadingClose();
	 });
}
//审核通过
function doPass(userId, matchType) {
    JY.Model.confirm("确认要审核通过吗?",function(){
        JY.Ajax.doRequest(null,jypath +'/jc/analyst/check',{userId:userId, matchType:matchType, state:2},function(data){
            JY.Model.info(data.resMsg,function(){search();});
        });
    });
}
//审核不通过
function doNoPass(userId, matchType) {
    JY.Model.confirm("确认要审核不通过吗?",function(){
        JY.Ajax.doRequest(null,jypath +'/jc/analyst/check',{userId:userId, matchType:matchType, state:3},function(data){
            JY.Model.info(data.resMsg,function(){search();});
        });
    });
}
