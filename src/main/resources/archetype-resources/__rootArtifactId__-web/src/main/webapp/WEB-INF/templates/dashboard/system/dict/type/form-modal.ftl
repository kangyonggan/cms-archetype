<#assign ctx="${(rca.contextPath)!''}">
<#assign isEdit=dictionaryType.id??>
<#assign modal_title="${isEdit?string('编辑字典类型', '添加新字典类型')}" />

<@override name="modal-body">
    <@c.form id="modal-form" action="${ctx}/dashboard/system/dict/type/${isEdit?string('update', 'save')}" token=true>
    <input type="hidden" id="old-type" value="${dictionaryType.type!''}"/>
        <@c.input name="type" value="${dictionaryType.type!''}" label="字典类型" required=true valid={"rangelength": "[1, 20]"}/>
        <@c.input name="name" value="${dictionaryType.name!''}" label="类型名称" required=true valid={"rangelength": "[1, 32]"}/>
    </@c.form>
</@override>

<@override name="modal-footer">
    <@c.button name="取消" icon="fa-times" dismiss=true/>
    <@c.button name="提交" type="submit" class="btn-success" icon="fa-check"/>
<script src="${ctx}/static/app/js/dashboard/system/dict/type/form-modal.js"></script>
</@override>

<@extends name="../../../modal-layout.ftl"/>