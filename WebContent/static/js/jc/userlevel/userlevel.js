$(function () {
    //下拉框
    JY.Dict.setSelect("selectisValid,matchTypeId,userLevelId,upgradeTypeId","isValid,matchType,userLevel,upgradeType",2,"全部");
    var dictData = JY.Dict.getData("userLevel,matchType,upgradeType");
    userLevel = dictData['userLevel']['items']; //字典
    matchType = dictData['matchType']['items']; //字典
    upgradeType = dictData['upgradeType']['items']; //字典
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
        var url = jypath + '/jc/userlevel/toAdd';
        art.dialog.open(url, {
            title: '新增', width: 450, height: 500,
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
                JY.Ajax.doRequest(null, jypath + '/jc/userlevel/deleteBatch', {chks: chks.toString()}, function (data) {
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
    JY.Ajax.doRequest("baseForm", jypath + '/jc/userlevel/findByPage', null, function (data) {
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
                html+="<td class='center'><label> <input type='checkbox' name='ids' value='"+l.id+"' class='ace' /> <span class='lbl'></span></label></td>";
                html+="<td class='center'>"+(i+leng+1)+"</td>";
                html+="<td class='center'>"+JY.Object.notEmpty(l.mobile)+"</td>";
                html+="<td class='center'>"+JY.Object.notEmpty(l.nick_name)+"</td>";
                html+="<td class='center'>"+JY.Object.notEmpty(JY.Dict.getName(matchType,l.matchType))+"</td>";
                html+="<td class='center'>"+JY.Object.notEmpty(JY.Dict.getName(userLevel,l.level))+"</td>";
                html+="<td class='center'>"+JY.Object.notEmpty(JY.Dict.getName(upgradeType,l.upgradeType))+"</td>";
                html+="<td class='center'>"+JY.Object.notEmpty(l.betCnt)+"</td>";
                html+="<td class='center'>"+JY.Object.notEmpty(l.winCnt)+"/"+JY.Object.notEmpty(l.loseCnt)+"</td>";
                html+="<td class='center'>"+JY.Object.notEmpty(l.profitRate)+"</td>";
                html+="<td class='center'>"+JY.Date.Default(l.createTime)+"</td>";
                html+="<td class='center'>"+JY.Date.Default(l.updateTime)+"</td>";
                html+="<td class='center'>"+JY.Object.notEmpty(l.sort)+"</td>";
                /*if(l.isValid==1) html+="<td class='center hidden-480'><span class='label label-sm label-success'>有效</span></td>";
                else             html+="<td class='center hidden-480'><span class='label label-sm arrowed-in'>无效</span></td>";
                */
                html+=JY.Tags.setFunction(l.id,permitBtn); //给按钮传主键
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

function findBtn(id) {
    var url = jypath + '/jc/userlevel/toAdd?type=find&id=' + id;
    art.dialog.open(url, {
        title: '查看', width: 450, height: 550
    });
}

//单条删除
function deleteBtn(id) {
    JY.Model.confirm("确认删除吗？", function () {
        JY.Ajax.doRequest(null, jypath + '/jc/userlevel/delete', {id: id}, function (data) {
            JY.Model.info(data.resMsg, function () {
                search();
            });
        });
    });
}
//批量删除
function updateBtn(id) {
    var url = jypath + '/jc/userlevel/toAdd?id=' + id;
    art.dialog.open(url, {
        title: '修改', width: 450, height: 500,
        close: function() {
            search();
        }
    });
}


function setSelect(ids, keys, type, dfstr,selectedValues) {
    $.ajax({
        type: 'POST',
        url: jypath + '/backstage/dataDict/getDictSelect',
        data: {ids: ids, keys: keys},
        dataType: 'json',
        success: function (data, textStatus) {
            if (data.res == 1) {
                var map = data.obj;
                var idss = ids.split(",");
                var opts = "", name = "";
                if (type == 1) {
                    for (var i = 0; i < idss.length; i++) {
                        name = map[idss[i]].name;
                        opts = "<option value=''>请选择</option>";
                        $.each(map[idss[i]].items, function (n, v) {
                            opts += "<option value='" + v.value + "'>" + v.name + "</option>";
                        });
                        $("#" + idss[i] + " select").append(opts);
                        $("#" + idss[i]).trigger("liszt:updated");
                    }
                    ;
                }
                else if (type == 2) {
                    var dfstrs = dfstr.split(",");
                    for (var i = 0; i < idss.length; i++) {
                        name = map[idss[i]].name;
                        $("#" + idss[i] + " label").html(name);
                        opts = "<option value=''>" + dfstrs + "</option>";
                        $.each(map[idss[i]].items, function (n, v) {
                            opts += "<option value='" + v.value + "'>" + v.name + "</option>";
                        });
                        $("#" + idss[i] + " select").append(opts);
                    }
                } else if(type == 3){
                    var values = selectedValues.split(",");
                    for (var i = 0; i < idss.length; i++) {
                        opts = "<option value=''>请选择</option>";
                        $.each(map[idss[i]].items, function (n, v) {
                            if(values[n] == v.value) {
                                opts += "<option value='" + v.value + "' selected>" + v.name + "</option>";
                            } else {
                                opts += "<option value='" + v.value + "'>" + v.name + "</option>";
                            }
                        });
                        $("#" + idss[i] + " select").append(opts);
                    }
                }
                else {
                    for (var i = 0; i < idss.length; i++) {
                        var name = map[idss[i]].name;
                        $("#" + idss[i] + " label").html(name);
                        opts = "";
                        $.each(map[idss[i]].items, function (n, v) {
                            opts += "<option value='" + v.value + "'>" + v.name + "</option>";
                        });
                        $("#" + idss[i] + " select").append(opts);
                    }
                }
            }
            //适应手机
            if ("ontouchend" in document) {
                $(".chosen-select").removeClass("chosen-select");
            }
            //下拉框样式
            else {
                $(".chosen-select").chosen();
                $(".chosen-select-deselect").chosen({allow_single_deselect: true});
            }
        }
    });
}


function isValid(formId, val) {
    $("#" + formId + " .isValidCheckbox [hi-isValid]").val(val);
    if (val == 1) {
        $("#" + formId + " .isValidCheckbox [sh-isValid]").prop("checked", true);
    } else {
        $("#" + formId + " .isValidCheckbox [sh-isValid]").prop("checked", false);
    }
}


function doRequest(form, url, param, fn) {
    var params = form || param || {};
    if (typeof form == 'string') {
        params = $.extend(param || {}, JY.Object.serialize($("#" + form)), {menu: JY.Url.getParam("menu")});
    }
    $.ajax({
        type: 'POST', url: url, data: params, dataType: 'json', success: function (data, textStatus) {
            if (data.res == 1) {
                if (typeof(fn) == 'function') {
                    fn.call(this, data);
                }
            } else {
                if (JY.Object.notNull(data.resMsg))JY.Model.error(data.resMsg);
            }
        }, error: function () {
            return;
        }, beforeSend: function () {
        }, complete: function () {
        }
    });
}

function req(form, url, param, fn) {
    var params = form || param || {};
    if (typeof form == 'string') {
        params = $.extend(param || {}, JY.Object.serialize($("#" + form)), {menu: JY.Url.getParam("menu")});
    }
    $.ajax({
        type: 'POST', url: url, data: params, dataType: 'json', success: function (data, textStatus) {
            if (typeof(fn) == 'function') {
                fn.call(this, data);
            }
        }, error: function () {
            return;
        }, beforeSend: function () {
        }, complete: function () {
        }
    });
}
