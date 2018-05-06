{{if value==1}}
<@c.link name="<span class='label label-danger arrowed-in'>已删除</span>"
href="${baseUrl}/{{row.id}}/deleted/0" type="confirm" title="恢复字典类型"/>
{{else}}
<@c.link name="<span class='label label-success arrowed-in'>未删除</span>"
href="${baseUrl}/{{row.id}}/deleted/1" type="confirm" title="删除字典类型"/>
{{/if}}