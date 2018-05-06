<#assign ctx="${(rca.contextPath)!''}">

<div class="space-10"></div>

<@c.form id="form" action="${ctx}/dashboard/user/info" multipart=true>
    <@c.tab_panel>
        <@c.tabs>
            <@c.tab ref="edit-basic" name="基础信息" icon="fa-pencil-square-o" active=true/>
            <@c.tab ref="edit-password" name="修改密码" icon="fa-key"/>
        </@c.tabs>

        <@c.tab_contents>
            <@c.tab_content id="edit-basic" active=true>
                <@c.input name="username" value="${user.username}" label="用户名" readonly=true/>
                <@c.input name="realname" value="${user.realname}" label="真实姓名" required=true valid={"rangelength": "[1, 32]"}/>

                <div class="form-group">
                    <label class="col-md-3 app-label nowrap">上传头像</label>

                    <div class="col-md-7 controls">
                        <input type="file" name="file" class="ace ace-file-input"/>
                        <div>请上传 png、gif、jpg 格式的图片文件，文件大小不能超过10MB。建议上传一张 240*240 像素或等比例的图片。</div>
                    </div>
                </div>
            </@c.tab_content>
            <@c.tab_content id="edit-password">
                <@c.input name="password" type="password" label="新密码" required=true valid={"isPassword": "true"}/>
                <@c.input name="rePassword" type="password" label="确认密码" required=true valid={"equalTo": "#password"}/>
            </@c.tab_content>
        </@c.tab_contents>
    </@c.tab_panel>

    <@c.form_actions>
        <@c.button name="提交" class="btn-success" icon="fa-check"/>
    </@c.form_actions>
</@c.form>

<script src="${ctx}/static/app/js/dashboard/user/info/index.js"></script>
