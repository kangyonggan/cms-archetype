<#assign ctx="${(rca.contextPath)!''}">
<#assign modal_title="修改密码" />

<@override name="modal-body">
    <@c.form id="modal-form" action="${ctx}/dashboard/system/user/password" token=true>
        <@c.input name="username" value="${user.username!''}" label="用户名" readonly=true/>
        <@c.input name="realname" value="${user.realname!''}" label="真实姓名" readonly=true/>

        <@c.input name="password" type="password" label="密码" required=true valid={"isPassword": "true"}/>
        <@c.input name="rePassword" type="password" label="确认密码" required=true valid={"equalTo": '#password'}/>
    </@c.form>
</@override>

<@override name="modal-footer">
    <@c.button name="取消" icon="fa-times" dismiss=true/>
    <@c.button name="提交" type="submit" class="btn-success" icon="fa-check"/>
<script src="${ctx}/static/app/js/dashboard/system/user/password-modal.js"></script>
</@override>

<@extends name="../../modal-layout.ftl"/>