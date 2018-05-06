<#assign ctx="${(rca.contextPath)!''}">
<#assign isEdit=role.code??>
<#assign modal_title="${isEdit?string('编辑角色', '添加新角色')}" />

<@override name="modal-body">
    <@c.form id="modal-form" action="${ctx}/dashboard/system/role/${isEdit?string('update', 'save')}" token=true>
    <input type="hidden" id="old-code" value="${role.code!''}"/>
        <@c.input name="code" value="${role.code!''}" label="角色代码" readonly=isEdit
        required=!isEdit placeholder="格式参考:ROLE_ADMIN" valid={"isRoleCode": "true"}/>
        <@c.input name="name" value="${role.name!''}" label="角色名称" required=true valid={"rangelength": "[1, 32]"}/>

    </@c.form>
</@override>

<@override name="modal-footer">
    <@c.button name="取消" icon="fa-times" dismiss=true/>
    <@c.button name="提交" type="submit" class="btn-success" icon="fa-check"/>
<script src="${ctx}/static/app/js/dashboard/system/role/form-modal.js"></script>
</@override>

<@extends name="../../modal-layout.ftl"/>