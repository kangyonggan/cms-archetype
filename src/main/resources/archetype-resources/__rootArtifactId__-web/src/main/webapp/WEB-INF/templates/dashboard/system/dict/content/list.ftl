<#assign ctx="${(rca.contextPath)!''}">
<#assign baseUrl="${ctx}/dashboard/system/dict/content"/>

<@c.form class="col-xs-12 fa-border radius-base">
    <@c.select name="query.type" label="字典类型" inline=true>
        <#list dictionaryTypes as dictionaryType>
        <option value="${dictionaryType.type}">${dictionaryType.name}</option>
        </#list>
    </@c.select>
    <@c.input name="query.code" label="字典代码" inline=true/>
    <@c.input name="query.value" label="字典的值" inline=true/>
    <@c.form_actions background=false>
        <@c.link name="查询" class="btn btn-sm btn-purple" icon="fa-search" type="submit" table_id="table"/>
        <@c.link name="清除" class="btn btn-sm btn-warning" icon="fa-undo" type="reset"/>
        <@c.link name="新增字典" class="btn btn-sm btn-skin" href="${baseUrl}/create" modal="myModal"/>
        <@c.link name="批量删除" class="btn btn-sm btn-danger" click="deleteBatch"/>
    </@c.form_actions>
</@c.form>

<@c.table url="${baseUrl}/list">
    <@c.th checkbox=true/>
    <@c.th field="id" title="ID" class="hidden-sm hidden-xs"/>
    <@c.th field="type" title="字典类型" />
    <@c.th field="code" title="字典代码" sortable=true/>
    <@c.th field="value" title="字典的值" sortable=true/>
    <@c.th field="sort" title="排序" sortable=true/>
    <@c.th field="isDeleted" title="逻辑删除" render=true>
        <#include "delete.ftl"/>
    </@c.th>
    <@c.th field="createdTime" title="创建时间" render=true class="hidden-sm hidden-xs">
        <@c.thFormat type="datetime"/>
    </@c.th>
    <@c.th title="操作" render=true>
        <#include "operation.ftl"/>
    </@c.th>
</@c.table>

<script src="${ctx}/static/app/js/dashboard/system/dict/content/list.js"></script>