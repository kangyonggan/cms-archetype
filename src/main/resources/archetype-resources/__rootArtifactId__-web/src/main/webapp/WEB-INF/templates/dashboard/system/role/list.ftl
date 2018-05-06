<#assign ctx="${(rca.contextPath)!''}">
<#assign baseUrl="${ctx}/dashboard/system/role"/>

<@c.form class="col-xs-12 fa-border radius-base">
    <@c.input name="query.code" label="角色代码" inline=true/>
    <@c.input name="query.name" label="角色名称" inline=true/>
    <@c.input name="query.beginDate" label="创建开始日期" inline=true class="date-picker" readonly=true/>
    <@c.input name="query.endDate" label="创建结束日期" inline=true class="date-picker" readonly=true/>
    <@c.form_actions background=false>
        <@c.link name="查询" class="btn btn-sm btn-purple" icon="fa-search" type="submit" table_id="table"/>
        <@c.link name="清除" class="btn btn-sm btn-warning" icon="fa-undo" type="reset"/>
        <@c.link name="新增角色" class="btn btn-sm btn-skin" href="${baseUrl}/create" modal="myModal"/>
    </@c.form_actions>
</@c.form>

<@c.table url="${baseUrl}/list">
    <@c.th field="id" title="ID" class="hidden-sm hidden-xs"/>
    <@c.th field="code" title="角色代码" />
    <@c.th field="name" title="角色名称" sortable=true/>
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