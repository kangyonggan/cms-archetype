<#assign ctx="${(rca.contextPath)!''}">
<#assign baseUrl="${ctx}/dashboard/system/preference"/>

<@c.form class="col-xs-12 fa-border radius-base">
    <@c.input name="query.username" label="用户名" inline=true/>
    <@c.select name="query.type" label="偏好类型" inline=true enum_key="preferenceType"/>
    <@c.input name="query.name" label="偏好名称" inline=true/>
    <@c.form_actions background=false>
        <@c.link name="查询" class="btn btn-sm btn-purple" icon="fa-search" type="submit" table_id="table"/>
        <@c.link name="清除" class="btn btn-sm btn-warning" icon="fa-undo" type="reset"/>
        <@c.link name="批量删除" class="btn btn-sm btn-danger" click="deleteBatch"/>
    </@c.form_actions>
</@c.form>

<@c.table url="${baseUrl}/list">
    <@c.th checkbox=true/>
    <@c.th field="type" title="偏好类型" render=true>
        <@c.thFormat type="enum" enum_key="preferenceType"/>
    </@c.th>
    <@c.th field="username" title="用户名" sortable=true/>
    <@c.th field="name" title="偏好名称" sortable=true/>
    <@c.th field="value" title="偏好的值" sortable=true/>
    <@c.th field="createdTime" title="创建时间" render=true class="hidden-sm hidden-xs">
        <@c.thFormat type="datetime"/>
    </@c.th>
    <@c.th title="操作" render=true>
        <#include "operation.ftl"/>
    </@c.th>
</@c.table>

<script src="${ctx}/static/app/js/dashboard/system/preference/list.js"></script>