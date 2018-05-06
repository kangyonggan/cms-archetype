<#assign ctx="${(rca.contextPath)!''}">

<div class="space-10"></div>

<@c.tab_panel>
    <@c.tabs>
        <@c.tab ref="component" name="UI组件" icon="fa-gavel" active=true/>
    </@c.tabs>

    <@c.tab_contents>
        <@c.tab_content id="component" active=true>
            <#include "component.ftl"/>
        </@c.tab_content>
    </@c.tab_contents>
</@c.tab_panel>

<script src="${ctx}/static/app/js/dashboard/help/index.js"></script>
