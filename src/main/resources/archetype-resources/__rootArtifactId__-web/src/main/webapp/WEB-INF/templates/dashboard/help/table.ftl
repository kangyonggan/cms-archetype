<p>
    表格使用的是bootstrap-table, 详见：<a href="http://bootstrap-table.wenzhixin.net.cn/" target="_blank">http://bootstrap-table.wenzhixin.net.cn/</a>
</p>

<@c.widget title="宏定义">
    <@c.modal title="表格的宏定义">
        <@c.code>
&lt;#macro table url id="table" pagination=true undefined_text="" form_id="form"&gt;
&lt;div class="form-table-space"&gt;&lt;/div&gt;
&lt;table id="${r'${id}'}" data-toggle="table" data-url="${r'${url}'}" data-pagination="${r'${pagination?string}'}"
       data-side-pagination="server" data-undefined-text="${r'${undefined_text}'}" data-striped="true"
       data-form-id="${r'${form_id}'}" data-click-to-select="true"&gt;
    &lt;thead&gt;
    &lt;tr&gt;
        &lt;#nested/&gt;
    &lt;/tr&gt;
    &lt;/thead&gt;
&lt;/table&gt;
&lt;script&gt;$('#${r'${id}'}').bootstrapTable();&lt;/script&gt;
&lt;/#macro&gt;
        </@c.code>
    </@c.modal>
    <@c.modal title="表头的宏定义">
        <@c.code>
&lt;#macro th title="" field="" class="" sortable=true render=false checkbox=false&gt;
&lt;th data-field="${r'${field}'}" class="${r'${class}'}"
    &lt;#if field!=''&gt;
    data-sortable="${r'${sortable?c}'}"
    &lt;/#if&gt;
    &lt;#if checkbox&gt;
        data-checkbox="true"
    &lt;/#if&gt;
    &lt;#if render&gt;
        &lt;#local uuid=app('uuid', 'th')/&gt;
        &lt;#local formatter=uuid + "Format"/&gt;
    data-formatter="${r'${formatter}'}"
    &lt;/#if&gt;
&gt;
    ${r'${title}'}
    &lt;#if render&gt;
        &lt;div id="${r'${uuid}'}" class="hidden"&gt;
            &lt;#nested/&gt;
        &lt;/div&gt;
        &lt;script&gt;
            function ${r'${formatter}'}(value, row, index) {
                var data = {"value": value, "row": row, "index": index};
                return template('${r'${uuid}'}', data);
            }
        &lt;/script&gt;
    &lt;/#if&gt;
&lt;/th&gt;
&lt;/#macro&gt;
        </@c.code>
    </@c.modal>

    <@c.modal title="单元格格式化的宏定义">
        <@c.code>
&lt;#macro thFormat type="" enum_key="" dict_type="" show_code=true&gt;
    &lt;#if type=="enum"&gt;
        &lt;#local uuid=app('uuid', 'func')/&gt;
    &lt;script&gt;
        var obj = {};
            &lt;#local map=enum('map', enum_key)/&gt;
            &lt;#if map?? && map?size gt 0&gt;
                &lt;#list map?keys as key&gt;
                obj["${r'${key}'}"] = "${r'${map[key]}'}";
                &lt;/#list&gt;
            &lt;/#if&gt;
        template.helper('${r'${uuid}'}', function (value) {
            for (var key in obj) {
                if (key == value) {
                    return obj[key]&lt;#if show_code&gt; + "[" + key + "]"&lt;/#if&gt;;
                }
            }
            return value;
        });
    &lt;/script&gt;
    {{value | ${r'${uuid}'}}
                &lt;#elseif type=="dict"&gt;
                    &lt;#local uuid=app('uuid', 'func')/&gt;
    &lt;script&gt;
        var arr = [];
            &lt;#local list=dict('list', dict_type)/&gt;
            &lt;#if list?? && list?size gt 0&gt;
                &lt;#list list as dict&gt;
                arr.push({"code": "${r'${dict.code}'}", "value": "${r'${dict.value}'}"});
                &lt;/#list&gt;
            &lt;/#if&gt;
        template.helper('${r'${uuid}'}', function (code) {
            for (var i in arr) {
                if (arr[i].code == code) {
                    return arr[i].value&lt;#if show_code&gt; + "[" + code + "]"&lt;/#if&gt;;
                }
            }
            return code;
        });
    &lt;/script&gt;
    {{value | ${r'${uuid}'}}
                &lt;#elseif type=="datetime"&gt;
    {{value | datetimeFormat}}
                &lt;#elseif type=="date"&gt;
    {{value | dateFormat}}
                &lt;#elseif type=="time"&gt;
    {{value | timeFormat}}
                &lt;#elseif type=="yesNo"&gt;
    {{if value==1}}
    是
    {{else}}
    否
    {{/if}}
                &lt;#else&gt;
    {{value}}
                &lt;/#if&gt;
&lt;/#macro&gt;
        </@c.code>
    </@c.modal>
</@c.widget>

<@c.widget title="表格实例">
    <@c.table url="${ctx}/dashboard/system/dict/content/list">
        <@c.th checkbox=true/>
        <@c.th field="id" title="ID" class="hidden-sm hidden-xs"/>
        <@c.th field="type" title="字典类型" />
        <@c.th field="code" title="字典代码" sortable=true/>
        <@c.th field="value" title="字典的值" sortable=true/>
        <@c.th field="sort" title="排序" sortable=true/>
        <@c.th field="isDeleted" title="逻辑删除" render=true>
            {{if value==1}}
                <@c.link name="<span class='label label-danger arrowed-in'>已删除</span>"
                    href="" type="confirm" title="恢复字典"/>
            {{else}}
                <@c.link name="<span class='label label-success arrowed-in'>未删除</span>"
                    href="" type="confirm" title="删除字典"/>
            {{/if}}
        </@c.th>
        <@c.th field="createdTime" title="创建时间" render=true class="hidden-sm hidden-xs">
            <@c.thFormat type="datetime"/>
        </@c.th>
        <@c.th title="操作" render=true>
            <@c.link_group>
                <@c.link name="编辑" href="${ctx}/dashboard/help/create" class="btn btn-xs btn-inverse" modal="myModal"/>
                <@c.drop_group>
                    {{if row.isDeleted==1}}
                        <@c.link name="彻底删除" drop=true href="" type="confirm" title="彻底删除字典"/>
                    {{else}}
                        <@c.link name="删除" drop=true href="" type="confirm" title="删除字典"/>
                    {{/if}}
                </@c.drop_group>
            </@c.link_group>
        </@c.th>
    </@c.table>

    <@c.code>
&lt;@c.table url="${r'${ctx}'}/dashboard/system/dict/content/list"&gt;
    &lt;@c.th checkbox=true/&gt;
    &lt;@c.th field="id" title="ID" class="hidden-sm hidden-xs"/&gt;
    &lt;@c.th field="type" title="字典类型" /&gt;
    &lt;@c.th field="code" title="字典代码" sortable=true/&gt;
    &lt;@c.th field="value" title="字典的值" sortable=true/&gt;
    &lt;@c.th field="sort" title="排序" sortable=true/&gt;
    &lt;@c.th field="isDeleted" title="逻辑删除" render=true&gt;
        {{if value==1}}
            &lt;@c.link name="&lt;span class='label label-danger arrowed-in'&gt;已删除&lt;/span&gt;"
            href="${ctx}/dashboard/system/dict/content/{{row.id}}/deleted/0" type="confirm" title="恢复字典"/&gt;
        {{else}}
            &lt;@c.link name="&lt;span class='label label-success arrowed-in'&gt;未删除&lt;/span&gt;"
            href="${ctx}/dashboard/system/dict/content/{{row.id}}/deleted/1" type="confirm" title="删除字典"/&gt;
        {{/if}}
    &lt;/@c.th&gt;
    &lt;@c.th field="createdTime" title="创建时间" render=true class="hidden-sm hidden-xs"&gt;
        &lt;@c.thFormat type="datetime"/&gt;
    &lt;/@c.th&gt;
    &lt;@c.th title="操作" render=true&gt;
        &lt;@c.link_group&gt;
            &lt;@c.link name="编辑" href="${r'${ctx}'}/dashboard/system/dict/content/{{row.id}}/edit" class="btn btn-xs btn-inverse" modal="myModal"/&gt;
            &lt;@c.drop_group&gt;
                {{if row.isDeleted==1}}
                    &lt;@c.link name="彻底删除" drop=true href="${r'${ctx}'}/dashboard/system/dict/content/{{row.id}}/remove" type="confirm" title="彻底删除字典"/&gt;
                {{else}}
                    &lt;@c.link name="删除" drop=true href="${r'${ctx}'}/dashboard/system/dict/content/{{row.id}}/deleted/1" type="confirm" title="删除字典"/&gt;
                {{/if}}
            &lt;/@c.drop_group&gt;
        &lt;/@c.link_group&gt;
    &lt;/@c.th&gt;
&lt;/@c.table&gt;
    </@c.code>
</@c.widget>