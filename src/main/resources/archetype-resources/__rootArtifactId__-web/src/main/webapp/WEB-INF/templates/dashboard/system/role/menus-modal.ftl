<#assign ctx="${(rca.contextPath)!''}">
<#assign modal_title="设置角色权限" />

<link rel="stylesheet" href="${ctx}/static/libs/ztree/css/zTreeStyle.css"/>

<@override name="modal-body">
    <@c.form id="modal-form" action="${ctx}/dashboard/system/role/${roleCode}/menus" token=true>
            <input type="hidden" name="menus" id="menus"/>
            <div class="control-group">
                <div>
                    <ul id="tree" class="ztree"></ul>
                </div>
            </div>
    </@c.form>
</@override>

<@override name="modal-footer">
    <@c.button name="取消" icon="fa-times" dismiss=true/>
    <@c.button name="提交" type="submit" class="btn-success" icon="fa-check"/>
<script>
    var zNodes = [
        <#list allMenus as menu>
            {
                id:${menu.id},
                pId:${menu.pid},
                name: "${menu.name}",
                code: "${menu.code}",
                open: true
                ${(roleMenus?? && roleMenus?seq_contains(menu.code))?string(", checked:true", "")}
            },
        </#list>];
</script>
<script src="${ctx}/static/libs/ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script src="${ctx}/static/libs/ztree/js/jquery.ztree.excheck-3.5.min.js"></script>
<script src="${ctx}/static/app/js/dashboard/system/role/menus-modal.js"></script>
</@override>

<@extends name="../../modal-layout.ftl"/>