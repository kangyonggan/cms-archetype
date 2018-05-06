<@c.link_group>
    <@c.link name="编辑" href="${baseUrl}/{{row.username}}/edit" class="btn btn-xs btn-inverse" modal="myModal"/>
    <@c.drop_group>
        <@c.link name="设置角色" drop=true href="${baseUrl}/{{row.username}}/roles" modal="myModal"/>
        <@c.link name="修改密码" drop=true href="${baseUrl}/{{row.username}}/password" modal="myModal"/>
        {{if row.isDeleted==1}}
            <@c.link name="彻底删除" drop=true href="${baseUrl}/{{row.username}}/remove" type="confirm" title="彻底删除用户"/>
        {{/if}}
    </@c.drop_group>
</@c.link_group>