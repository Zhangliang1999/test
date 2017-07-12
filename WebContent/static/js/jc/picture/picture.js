$(function () {
	//下拉框
	JY.Dict.setSelect("selectisValid","isValid",2,"全部");
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
	$('#addBtn').on('click', function(e) {
		//通知浏览器不要执行与事件关联的默认动作		
		e.preventDefault();
        var url = jypath +'/backstage/tool/webuploader/test/img';
        art.dialog.open(url, {title: '新增', width: 450, height: 400,
            close: function() {
                search();
            }
        });
	});
	//批量删除
	$('#deleteBatchBtn').on('click', function(e) {
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
				JY.Ajax.doRequest(null,jypath +'/jc/picture/deleteBatch',{chks:chks.toString()},function(data){
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
	JY.Ajax.doRequest("baseForm",jypath +'/jc/picture/findByPage',null,function(data){
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
            		 html+="<td class='center'><label> <input type='checkbox' name='ids' value='"+l.picId+"' class='ace' /> <span class='lbl'></span></label></td>";
            		 html+="<td class='center'>"+(i+leng+1)+"</td>";
            		 html+="<td class='center'>"+JY.Object.notEmpty(l.name)+"</td>";
/*
                     html+="<td class='center'>"+JY.Object.notEmpty(l.comment)+"</td>";
*/
                     html+="<td class='center' ><img src='"+ jypath +  JY.Object.notEmpty(l.path)+"' style='width:300px;'/></td>";
            		 html+="<td class='center'>"+JY.Date.Default(l.createTime)+"</td>";
            		 html+=JY.Tags.setFunction(l.picId,permitBtn); //给按钮传主键
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

//查看
function check(picId){
    alert(1)
	cleanForm();
	JY.Ajax.doRequest(null,jypath +'/jc/picture/roleTree',null,function(data){
		$.fn.zTree.init($("#roleTree"),{view:{dblClickExpand:false,selectedMulti:false,nameIsHTML:true},data:{simpleData:{enable: true}},callback:{onClick:clickRole}},data.obj);
		JY.Ajax.doRequest(null,jypath +'/jc/picture/find',{picId:picId},function(data){
		    setForm(data);
		    JY.Model.check("auDiv");       
		});
	});
}
//单条删除
function deleteBtn(picId){
	JY.Model.confirm("确认删除吗？",function(){	
		JY.Ajax.doRequest(null,jypath +'/jc/picture/delete',{picId:picId},function(data){
			JY.Model.info(data.resMsg,function(){search();});
		});
	});
}
//批量删除
function updateBtn(picId){
	cleanForm();
	JY.Ajax.doRequest(null,jypath +'/jc/picture/roleTree',null,function(data){
		$.fn.zTree.init($("#roleTree"),{view:{dblClickExpand:false,selectedMulti:false,nameIsHTML:true},data:{simpleData:{enable: true}},callback:{onClick:clickRole}},data.obj);
		JY.Ajax.doRequest(null,jypath +'/jc/picture/find',{picId:picId},function(data){
		    setForm(data);   
		    JY.Model.edit("auDiv","修改",function(){
		    	if(JY.Validate.form("auForm")){
					var that =$(this);
					JY.Ajax.doRequest("auForm",jypath +'/jc/picture/update',null,function(data){
					    that.dialog("close");
					    JY.Model.info(data.resMsg,function(){search();});	
					});
				}	
		    });
		});
	});
}