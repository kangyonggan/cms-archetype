<#assign ctx="${(rca.contextPath)!''}">

<div class="space-10"></div>
<div class="alert alert-block alert-success">
    <i class="ace-icon fa fa-check green"></i>

    欢迎使用<strong class="green"><@s.message "app.name"/></strong>。请点击左边菜单开始工作。👈

    <a class="pull-right" href="${ctx}/dashboard#help">
        开发手册
        <i class="ace-icon fa fa-book"></i>
    </a>
</div>

<script src="${ctx}/static/app/js/dashboard/index.js"></script>
