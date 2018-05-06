<#assign ctx="${(rca.contextPath)!''}">
<#assign isEdit=dictionary.id??>
<#assign modal_title="${isEdit?string('编辑字典', '添加新字典')}" />

<@override name="modal-body">
    <@c.form id="modal-form" action="${ctx}/dashboard/system/dict/content/${isEdit?string('update', 'save')}" token=true>
    <#if isEdit>
        <input type="hidden" name="id" value="${dictionary.id}"/>
    </#if>
    <input type="hidden" id="old-type" value="${dictionary.type!''}"/>
        <@c.select name="type" value="${dictionary.type!''}" label="字典类型" required=true>
            <#list dictionaryTypes as dictionaryType>
            <option value="${dictionaryType.type}"
                    <#if dictionary.type?? && dictionary.type==dictionaryType.type>selected</#if>>${dictionaryType.name}</option>
            </#list>
        </@c.select>
    <input type="hidden" id="old-code" value="${dictionary.code!''}"/>
        <@c.input name="code" value="${dictionary.code!''}" label="字典代码" required=true valid={"rangelength": "[1, 64]"}/>
        <@c.input name="value" value="${dictionary.value!''}" label="字典的值" required=true valid={"rangelength": "[1, 256]"}/>
        <@c.input name="sort" value="${dictionary.sort!'0'}" label="字典排序" required=true valid={"range": "[0, 9999]"}/>
    </@c.form>
</@override>

<@override name="modal-footer">
    <@c.button name="取消" icon="fa-times" dismiss=true/>
    <@c.button name="提交" type="submit" class="btn-success" icon="fa-check"/>
<script src="${ctx}/static/app/js/dashboard/system/dict/content/form-modal.js"></script>
</@override>

<@extends name="../../../modal-layout.ftl"/>