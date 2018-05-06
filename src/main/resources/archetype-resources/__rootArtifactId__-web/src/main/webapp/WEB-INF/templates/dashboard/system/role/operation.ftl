<@c.link_group>
    <@c.link name="编辑" href="${baseUrl}/{{row.code}}/edit" class="btn btn-xs btn-inverse" modal="myModal"/>
    <@c.drop_group>
        <@c.link name="设置权限" drop=true href="${baseUrl}/{{row.code}}/menus" modal="myModal"/>
        {{if row.isDeleted==1}}
            <@c.link name="彻底删除" drop=true href="${baseUrl}/{{row.code}}/remove" type="confirm" title="彻底删除角色"/>
        {{/if}}
    </@c.drop_group>
</@c.link_group>