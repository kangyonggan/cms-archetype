<#assign ctx="${(rca.contextPath)!''}">
<#assign modal_title="新增" />

<@override name="modal-body">
    <@c.form id="modal-form" action="${ctx}/dashboard/help/save">
        <@c.input name="code" label="代码" required=true valid={"rangelength": "[1, 32]"}/>
        <@c.input name="name" label="名称"/>
    </@c.form>
</@override>

<@override name="modal-footer">
    <@c.button name="取消" icon="fa-times" dismiss=true/>
    <@c.button name="提交" type="submit" class="btn-success" icon="fa-check"/>
<script src="${ctx}/static/app/js/dashboard/help/help-modal.js"></script>
</@override>

<@extends name="../modal-layout.ftl"/>
