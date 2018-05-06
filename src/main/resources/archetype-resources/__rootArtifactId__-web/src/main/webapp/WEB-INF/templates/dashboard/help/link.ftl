<@c.widget title="宏定义">
    <@c.modal title="超链接的宏定义">
        <@c.code>
&lt;#macro link name id="" href="javascript:" icon="" class="" drop=false type="" table_id="" modal="" backdrop="static" type="" title="" click=""&gt;
    &lt;#if drop&gt;
&lt;li&gt;
    &lt;/#if&gt;
&lt;a href="${r'${href}'}" &lt;#if id!=''&gt;id="${r'${id}'}"&lt;/#if&gt; class="${r'${class}'}" title="${r'${title}'}"
    &lt;#if type!=''&gt;
        data-type="${r'${type}'}"
    &lt;/#if&gt;
   &lt;#if table_id != ''&gt;
        data-table-id="${r'${table_id}'}"
   &lt;/#if&gt;
   &lt;#if click != ''&gt;
        onclick="${r'${click}'}(this)"
   &lt;/#if&gt;
   &lt;#if modal != ''&gt;
   data-toggle="modal"
   data-target="#${r'${modal}'}"
   &lt;/#if&gt;
   data-backdrop="${r'${backdrop}'}"
&gt;
    &lt;#if icon!=''&gt;
        &lt;i class="ace-icon fa ${r'${icon}'}"&gt;&lt;/i&gt;
    &lt;/#if&gt;
            ${r'${name}'}
&lt;/a&gt;
    &lt;#if drop&gt;
&lt;/li&gt;
    &lt;/#if&gt;
&lt;/#macro&gt;
        </@c.code>
    </@c.modal>
</@c.widget>

<@c.widget title="基础实例">
    <@c.link name="超链接" class=""/>
    <@c.link name="超链接" class="btn btn-sm btn-skin"/>
    <@c.link name="超链接" class="btn btn-warning" icon="fa-undo"/>
    <@c.link name="超链接" class="btn btn-lg btn-danger" icon="fa-search"/>

    <@c.code>&lt;@c.link name="超链接" href="xxx"/&gt;
&lt;@c.link name="超链接" class="btn btn-sm btn-skin" href="xxx"/&gt;
&lt;@c.link name="超链接" class="btn btn-warning" icon="fa-undo" href="xxx"/&gt;
&lt;@c.link name="超链接" class="btn btn-lg btn-danger" icon="fa-search" href="xxx"/&gt;
    </@c.code>

    <@c.alert type="success">
         在class中加入<code>.btn-skin</code>，超链接的颜色会随着系统皮肤的改变而改变。
    </@c.alert>
</@c.widget>
<@c.widget title="内置实例">
            <p>
                系统中内置了三个特殊功能的超链接，分别是<code>查询</code>、<code>清除</code>和<code>确认框</code>。
            </p>
            <p>
                <code>查询</code>可以根据绑定的<code>table_id</code>和所在表单，去查询符合表单条件的内容，并渲染到绑定的表格中。注意：type必须指定为submit。
                <@c.modal title="实现逻辑">
                    <@c.code>
$(document).on("click", "[data-type='submit']", function (e) {
    e.preventDefault();
    var $this = $(this);
    var $table = $("#" + $this.data("table-id"));

    var params = $this.parents("form").serializeForm();
    $table.bootstrapTable("refresh", {query: params});
    return false;
});
                    </@c.code>
                </@c.modal>
            </p>
            <p>
                <code>清除</code>就是清除所在表单的所有内容。注意：type必须指定为reset。
                <@c.modal title="实现逻辑">
                    <@c.code>
$(document).on("click", "[data-type='reset']", function (e) {
    e.preventDefault();
    var $this = $(this);
    var $form = $this.parents("form");

    $form.find("input").val("");
    $form.find("select").val("");
    $form.find(".chosen-single span").text("请选择一项");
    $form.find(".chosen-single abbr").remove();
    return false;
});
                    </@c.code>
                </@c.modal>
            </p>
            <p>
                <code>确认框</code>就是会弹出一个确认框，问你是否确定执行。注意：type必须指定为confirm，且必须用在table中。
                <@c.modal title="实现逻辑">
                    <@c.code>
$(document).on("click", "[data-type='confirm']", function (e) {
    e.preventDefault();
    var $this = $(this);
    var $table = $(this).parents("table");

    $.messager.confirm("提示", "确定" + $this.attr("title") + "吗?", function () {
        $.get($this.attr('href')).success(function () {
            Message.success("操作成功");
            var formId = $table.data("form-id");
            var params = $("#" + formId).serializeForm();
            $table.bootstrapTable("refresh");
        }).error(function () {
            Message.error("网络错误，请稍后重试");
        })
    });
    return false;
});
                    </@c.code>
                </@c.modal>
            </p>
    <@c.link name="查询" class="btn btn-sm btn-purple" icon="fa-search" type="submit" table_id="table"/>
    <@c.link name="清除" class="btn btn-sm btn-warning" icon="fa-undo" type="reset"/>
    <@c.link name="彻底删除" class="btn btn-sm btn-danger" href="xxx" type="confirm" title="彻底删除"/>

    <@c.code>&lt;@c.link name="查询" class="btn btn-sm btn-purple" icon="fa-search" type="submit" table_id="table"/&gt;
&lt;@c.link name="清除" class="btn btn-sm btn-warning" icon="fa-undo" type="reset"/&gt;
&lt;@c.link name="彻底删除" class="btn btn-sm btn-danger" href="xxx" type="confirm" title="彻底删除"//&gt;
    </@c.code>

    <@c.alert type="danger">
            这三个内置的特殊超链接只能在特殊情况下才能使用，不可随意乱用。
    </@c.alert>

            <p>内置实例的应用：</p>

    <@c.code>&lt;#assign ctx="${r"${(rca.contextPath)!''}"}"&gt;

&lt;@c.form class="col-xs-12 fa-border radius-base"&gt;
    &lt;@c.input name="query.username" label="用户名" inline=true/&gt;
    &lt;@c.input name="query.realname" label="真实姓名" inline=true/&gt;

    &lt;@c.form_actions background=false&gt;
        &lt;@c.link name="查询" class="btn btn-sm btn-purple" icon="fa-search" type="submit" table_id="table"/&gt;
        &lt;@c.link name="清除" class="btn btn-sm btn-warning" icon="fa-undo" type="reset"/&gt;
    &lt;/@c.form_actions&gt;
&lt;/@c.form&gt;

&lt;@c.table url="${r'${ctx}'}/dashboard/system/user/list"&gt;
    &lt;@c.th field="username" title="用户名" /&gt;
    &lt;@c.th field="realname" title="真实姓名" sortable=true/&gt;
    &lt;@c.th title="操作" render=true&gt;
        &lt;@c.link name="彻底删除" class="btn btn-xs btn-inverse" href="${r'${ctx}'}/dashboard/system/user/{{row.username}}/remove" type="confirm" title="彻底删除用户"/&gt;
    &lt;/@c.th&gt;
&lt;/@c.table&gt;
    </@c.code>
</@c.widget>
<@c.widget title="拓展实例">
    <p>整合下拉按钮组</p>

<div style="margin-left: 100px;">
    <@c.link_group>
        <@c.link name="编辑" href="${ctx}/dashboard/help/create" class="btn btn-xs btn-inverse" modal="myModal"/>
        <@c.drop_group>
            <@c.link name="设置权限" drop=true href="${ctx}/dashboard/help/create" modal="myModal"/>
            <@c.link name="彻底删除" drop=true href="xxx" type="confirm" title="彻底删除"/>
        </@c.drop_group>
    </@c.link_group>
</div>

    <@c.code>
&lt;@c.link_group>
    &lt;@c.link name="编辑" href="${r'${ctx}'}/dashboard/help/create" class="btn btn-xs btn-inverse" modal="myModal"/&gt;
    &lt;@c.drop_group&gt;
        &lt;@c.link name="设置权限" drop=true href="${r'${ctx}'}/dashboard/help/create" modal="myModal"/&gt;
        &lt;@c.link name="彻底删除" drop=true href="xxx" type="confirm" title="彻底删除"/&gt;
    &lt;/@c.drop_group&gt;
&lt;/@c.link_group&gt;
    </@c.code>

    <p>整合模态框</p>
    <@c.link name="新增" class="btn btn-sm btn-skin" href="${ctx}/dashboard/help/create" modal="myModal"/>

    <@c.code>&lt;@c.link name="新增" class="btn btn-sm btn-skin" href="${r'${ctx}'}/dashboard/help/create" modal="myModal"/&gt;</@c.code>

</@c.widget>