<#assign ctx="${(rca.contextPath)!''}">
<#assign modal_title="编辑偏好" />

<@override name="modal-body">
    <@c.form id="modal-form" action="${ctx}/dashboard/system/preference/update" token=true>
        <@c.input name="type" value="${preference.type!''}" label="偏好类型" readonly=true/>
        <@c.input name="name" value="${preference.name!''}" label="偏好名称" readonly=true/>
        <@c.input name="value" value="${preference.value!''}" label="偏好的值" required=true valid={"rangelength": "[1, 256]"}/>
    </@c.form>
</@override>

<@override name="modal-footer">
    <@c.button name="取消" icon="fa-times" dismiss=true/>
    <@c.button name="提交" type="submit" class="btn-success" icon="fa-check"/>
<script src="${ctx}/static/app/js/dashboard/system/preference/form-modal.js"></script>
</@override>

<@extends name="../../modal-layout.ftl"/>