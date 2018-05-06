<#assign ctx="${(rca.contextPath)!''}">
<#assign baseUrl="${ctx}/dashboard/monitor/login"/>

<@c.form class="col-xs-12 fa-border radius-base">
    <@c.input name="query.username" label="用户名" inline=true/>
    <@c.input name="query.ip" label="登录IP" inline=true/>
    <@c.input name="query.beginDate" label="登录开始日期" inline=true class="date-picker" readonly=true/>
    <@c.input name="query.endDate" label="登录结束日期" inline=true class="date-picker" readonly=true/>

    <@c.form_actions background=false>
        <@c.link name="查询" class="btn btn-sm btn-purple" icon="fa-search" type="submit" table_id="table"/>
        <@c.link name="清除" class="btn btn-sm btn-warning" icon="fa-undo" type="reset"/>
    </@c.form_actions>
</@c.form>

<@c.table url="${baseUrl}/list">
    <@c.th field="id" title="ID" class="hidden-sm hidden-xs"/>
    <@c.th field="username" title="用户名" />
    <@c.th field="ip" title="登录IP" sortable=true/>
    <@c.th field="createdTime" title="登录时间" render=true class="hidden-sm hidden-xs">
        <@c.thFormat type="datetime"/>
    </@c.th>
</@c.table>