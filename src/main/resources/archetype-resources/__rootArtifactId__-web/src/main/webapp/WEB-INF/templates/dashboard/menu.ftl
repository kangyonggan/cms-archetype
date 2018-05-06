<li class="${sideBarHover} ${sideBarHighlight}">
    <a <#if menu.leaf?? && menu.leaf?size gt 0>class="dropdown-toggle" href="javascript:"
       <#else>href="#${menu.url}"</#if> data-url="${menu.url}">
        <#if menu.icon != ''>
            <i class="${menu.icon}"></i>
        <#else>
            <i class="menu-icon fa fa-caret-right"></i>
        </#if>
        <span class="menu-text">
        ${menu.name}
                </span>

    <#if menu.leaf?? && menu.leaf?size gt 0>
        <b class="arrow fa fa-angle-down"></b>
    </#if>
    </a>

    <b class="arrow"></b>

<#if menu.leaf?? && menu.leaf?size gt 0>
    <ul class="submenu">
        <#list menu.leaf as menu>
            <#--递归，可以实现无限级菜单-->
            <#include "menu.ftl"/>
        </#list>
    </ul>
</#if>
</li>