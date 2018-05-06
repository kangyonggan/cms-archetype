<#if !noSidebar??>
<div id="sidebar" class="sidebar responsive ${app('preference', 'ace', 'fixed-sidebar')} ${(app('preference', 'ace', 'compact')=='true')?string('compact', '')}">
    <div class="sidebar-shortcuts" id="sidebar-shortcuts">
        <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
            <button class="btn btn-success">
                <i class="ace-icon fa fa-signal"></i>
            </button>

            <button class="btn btn-info">
                <i class="ace-icon fa fa-pencil"></i>
            </button>

            <button class="btn btn-warning">
                <i class="ace-icon fa fa-users"></i>
            </button>

            <button class="btn btn-danger">
                <i class="ace-icon fa fa-cogs"></i>
            </button>
        </div>

        <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
            <span class="btn btn-success"></span>

            <span class="btn btn-info"></span>

            <span class="btn btn-warning"></span>

            <span class="btn btn-danger"></span>
        </div>
    </div>

    <ul class="nav nav-list">
        <#assign sideBarHover=(app('preference', 'ace', 'hover')=='true')?string('hover', '')/>
        <#assign sideBarHighlight=(app('preference', 'ace', 'highlight')=='true')?string('highlight', '')/>
        <#if _menus?size gt 0>
            <li class="${sideBarHover} ${sideBarHighlight}">
                <a data-url="index" href="${ctx}/dashboard#index">
                    <i class="menu-icon fa fa-dashboard"></i>
                    <span class="menu-text">工作台</span>
                </a>
            </li>
            <#list _menus[0].leaf as menu>
                <#include "menu.ftl"/>
            </#list>
        </#if>
    </ul>

    <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
        <i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left"
           data-icon2="ace-icon fa fa-angle-double-right"></i>
    </div>
</div>
</#if>