$(function () {
    updateState("system/menu");

    var $form = $('#form');

    var beforeEditName = function () {
        return false;
    };

    var beforeRemove = function (treeId, treeNode) {
        $.messager.confirm("提示", "确定删除" + treeNode.name + "吗?", function () {
            $("#menuId").val(treeNode.id);
            formSubmit($form, null, function () {
                var treeObj = $.fn.zTree.getZTreeObj("menu-tree");
                treeObj.removeNode(treeNode);
            })

        });

        return false;
    };

    var addHoverDom = function (treeId, treeNode) {
        var sObj = $("#" + treeNode.tId + "_span");
        if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0) {
            return;
        }
        var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
            + "' title='add'></span>";
        sObj.after(addStr);

        var addBtn = $("#addBtn_" + treeNode.tId);
        if (addBtn) {
            addBtn.bind("click", function () {
                $("#myModal").modal({
                    backdrop: 'static',
                    remote: ctx + '/dashboard/system/menu/create?pcode=' + treeNode.code
                });
            });
        }

        var editBtn = $("#" + treeNode.tId + "_edit");
        if (editBtn) {
            editBtn.bind("click", function () {
                $("#myModal").modal({
                    remote: ctx + '/dashboard/system/menu/' + treeNode.id + '/edit'
                });
            });
        }
    };

    var removeHoverDom = function (treeId, treeNode) {
        $("#addBtn_" + treeNode.tId).unbind().remove();
    };

    var setting = {
        view: {
            addHoverDom: addHoverDom,
            removeHoverDom: removeHoverDom
        },
        edit: {
            enable: true
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            beforeEditName: beforeEditName,
            beforeRemove: beforeRemove
        }
    };

    $.fn.zTree.init($("#menu-tree"), setting, zNodes);

});