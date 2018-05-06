<#assign ctx="${(rca.contextPath)!''}">
<#assign isEdit=user.username??>
<#assign modal_title="${isEdit?string('编辑用户', '添加新用户')}" />

<@override name="modal-body">
    <@c.form id="modal-form" action="${ctx}/dashboard/system/user/${isEdit?string('update', 'save')}" token=true>
        <input type="hidden" id="old-username" value="${user.username!''}"/>
        <@c.input name="username" value="${user.username!''}" label="用户名" readonly=isEdit required=!isEdit valid={"isUsername": "true"}/>
        <@c.input name="realname" value="${user.realname!''}" label="真实姓名" required=true valid={"rangelength": "[1, 32]"}/>

        <#if !isEdit>
            <@c.input name="password" type="password" label="密码" required=true valid={"isPassword": "true"}/>
            <@c.input name="rePassword" type="password" label="确认密码" required=true valid={"equalTo": '#password'}/>
        </#if>
    </@c.form>
</@override>

<@override name="modal-footer">
    <@c.button name="取消" icon="fa-times" dismiss=true/>
    <@c.button name="提交" type="submit" class="btn-success" icon="fa-check"/>
<script src="${ctx}/static/app/js/dashboard/system/user/form-modal.js"></script>
</@override>

<@extends name="../../modal-layout.ftl"/>