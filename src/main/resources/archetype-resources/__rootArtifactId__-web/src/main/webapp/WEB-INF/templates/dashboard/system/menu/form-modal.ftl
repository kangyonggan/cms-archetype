<#assign ctx="${(rca.contextPath)!''}">
<#assign isEdit=menu.id??>
<#assign modal_title="${menu.id???string('修改菜单', '添加菜单')}" />

<@override name="modal-body">
    <@c.form id="modal-form" action="${ctx}/dashboard/system/menu/${isEdit?string('update', 'save')}" token=true>
        <#if menu.id??>
        <input type="hidden" name="id" value="${menu.id}"/>
        </#if>
        <#if parentMenu??>
            <input type="hidden" name="pcode" value="${(parentMenu.code)!''}">
            <@c.input name="" value="${parentMenu.name!''}" label="上级菜单" readonly=true/>
        </#if>

        <@c.input name="name" value="${menu.name!''}" label="菜单名称" required=true valid={"rangelength": "[1, 32]"}/>
        <@c.input name="code" value="${menu.code!''}" label="菜单代码" required=true placeholder="格式参考:SYSTEM_USER" valid={"isMenuCode": "true"}/>
        <#if menu.id??>
        <input type="hidden" id="old-code" value="${menu.code}"/>
        </#if>
        <@c.input name="url" value="${menu.url!''}" label="菜单地址" required=true placeholder="格式参考:system/user" valid={"isMenuUrl": "true"}/>
        <@c.input name="sort" value="${menu.sort!'0'}" label="排序" required=true placeholder="0排在最上面" valid={"range": "[0, 100]"}/>
        <@c.input name="icon" value="${menu.icon!''}" label="图标" placeholder="格式参考:menu-icon fa fa-dashboard"/>
    </div>
    </@c.form>
</@override>

<@override name="modal-footer">
    <@c.button name="取消" icon="fa-times" dismiss=true/>
    <@c.button name="提交" type="submit" class="btn-success" icon="fa-check"/>
<script src="${ctx}/static/app/js/dashboard/system/menu/form-modal.js"></script>
</@override>

<@extends name="../../modal-layout.ftl"/>