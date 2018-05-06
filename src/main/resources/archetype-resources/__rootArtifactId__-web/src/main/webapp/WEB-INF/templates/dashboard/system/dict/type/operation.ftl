<@c.link_group>
    <@c.link name="编辑" href="${baseUrl}/{{row.id}}/edit" class="btn btn-xs btn-inverse" modal="myModal"/>
    <@c.drop_group>
        {{if row.isDeleted==1}}
            <@c.link name="彻底删除" drop=true href="${baseUrl}/{{row.id}}/remove" type="confirm" title="彻底删除字典类型"/>
        {{/if}}
    </@c.drop_group>
</@c.link_group>