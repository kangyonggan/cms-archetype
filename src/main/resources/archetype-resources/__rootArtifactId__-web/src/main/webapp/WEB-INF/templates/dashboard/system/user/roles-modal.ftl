<#assign modal_title="设置角色" />

<@override name="modal-body">
<@c.form id="modal-form" action="${ctx}/dashboard/system/user/${username}/roles" token=true>
    <div class="control-group">
        <#list allRoles as role>
            <div class="checkbox">
                <label>
                    <input name="roles" type="checkbox" value="${role.code}"
                           class="ace" ${userRoles?seq_contains(role.code)?string("checked", "")}/>
                    <span class="lbl"> ${role.name} </span>
                </label>
            </div>
        </#list>
    </div>
</@c.form>
</@override>

<@override name="modal-footer">
    <@c.button name="取消" icon="fa-times" dismiss=true/>
    <@c.button name="提交" type="submit" class="btn-success" icon="fa-check"/>
<script src="${ctx}/static/app/js/dashboard/system/user/roles-modal.js"></script>
</@override>

<@extends name="../../modal-layout.ftl"/>