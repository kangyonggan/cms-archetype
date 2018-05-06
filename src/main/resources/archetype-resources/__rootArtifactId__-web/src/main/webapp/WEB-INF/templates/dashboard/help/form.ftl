<p>
    表单分为两种，一种是用于搜索的表单，他的特点是宽度为33.33%，也就是一行最多有3个输入框。
    还有一种是普通的表单，一行只有一个输入框。
    不管是哪种表单都支持响应式。
</p>
<@c.widget title="宏定义">
    <@c.modal title="表单的宏定义">
        <@c.code>
&lt;#macro form action="" id="form" action="" method="post" class="" multipart=false&gt;
&lt;form method="${r'${method}'}" class="form-horizontal ${r'${class}'}"
      &lt;#if multipart>enctype="multipart/form-data"&lt;/#if&gt;
      &lt;#if action!=''>action="${r'${action}'}"&lt;/#if&gt;
      &lt;#if id!=''>id="${r'${id}'}"&lt;/#if&gt;
&gt;
    &lt;div class="space-6"&gt;&lt;/div&gt;
    &lt;#nested /&gt;
&lt;/form&gt;
&lt;/#macro&gt;
        </@c.code>
    </@c.modal>
    <@c.modal title="输入框的宏定义">
        <@c.code>
&lt;#macro input name label value="" inline=false class="" placeholder="" readonly=false type="text" required=false valid={}&gt;
&lt;div class="form-group &lt;#if inline>col-lg-4 col-md-6 col-xs-12&lt;/#if&gt;"&gt;
    &lt;div class="app-label nowrap &lt;#if inline&lt;col-md-5 col-xs-12&lt;#else>col-md-3&lt;/#if&gt;"&gt;
        &lt;label class="&lt;#if required>required&lt;/#if&gt;"&gt;${r'${label}'}&lt;/label&gt;
    &lt;/div&gt;
    &lt;div class="col-md-7 controls &lt;#if inline&gt;col-xs-12&lt;/#if&gt;"&gt;
        &lt;input type="${r'${type}'}" id="${r'${name}'}" &lt;#if readonly&gt;readonly&lt;/#if&gt; name="${r'${name}'}"
        &lt;#list valid?keys as nm>
            ${r'${nm}'}="${r"${valid[nm]}"}"
        &lt;/#list>
        value="${r'${value}'}" class="form-control ${r'${class}'} &lt;#if readonly&gt;readonly&lt;/#if&gt;"
        placeholder="${r"${(placeholder=='')?string('请输入${label}', placeholder)}"}" &lt;#if required&gt;required&lt;/#if&gt;/&gt;
    &lt;/div&gt;
    &lt;#if class?contains("date-picker")&gt;
        &lt;script&gt;
            $('.date-picker').datepicker();
        &lt;/script&gt;
    &lt;/#if&gt;
&lt;/div&gt;
&lt;/#macro&gt;
        </@c.code>
    </@c.modal>
    <@c.modal title="选择框的宏定义">
        <@c.code>
&lt;#macro select name label value="" inline=false class="" placeholder=""
readonly=false type="single" required=false enum_key="" dict_type="" show_code=true chosen=true&gt;
&lt;div class="form-group &lt;#if inline&gt;col-lg-4 col-md-6 col-xs-12&lt;/#if&gt;"&gt;
    &lt;div class="app-label nowrap &lt;#if inline&gt;col-md-5 col-xs-12&lt;#else&gt;col-md-3&lt;/#if&gt;"&gt;
        &lt;label class="&lt;#if required&gt;required&lt;/#if&gt;"&gt;${r'${label}'}&lt;/label&gt;
    &lt;/div&gt;
    &lt;div class="col-md-7 controls &lt;#if inline&gt;col-xs-12&lt;/#if&gt;"&gt;
        &lt;#local id=(name?contains("."))???string('${r"${name?substring(name?index_of('.') + 1)}"}', '${r'${name}'}')/&gt;
        &lt;select id="${r'${id}'}" name="${r'${name}'}"
                class="form-control &lt;#if chosen&gt;chosen-select&lt;/#if&gt; ${r'${class}'} &lt;#if readonly&gt;readonly&lt;/#if&gt;"&gt;

            &lt;option value=""&gt;&lt;/option&gt;

            &lt;#if enum_key != ""&gt;
                &lt;#local map=enum('map', enum_key)/&gt;
                &lt;#if map?? && map?size gt 0&gt;
                    &lt;#list map?keys as key&gt;
                        &lt;option value="${r'${key'}" &lt;#if value==key&gt;selected&lt;/#if&gt;&gt;
                            ${r'${map[key]}'}&lt;#if show_code&gt;[${r'${key}'}]&lt;/#if&gt;
                        &lt;/option&gt;
                    &lt;/#list&gt;
                &lt;/#if&gt;
            &lt;#elseif dict_type != ""&gt;
                &lt;#local list=dict('list', dict_type)/&gt;
                &lt;#if list?? && list?size gt 0&gt;
                    &lt;#list list as dict&gt;
                        &lt;option value="${r'${dict.code}'}" &lt;#if value==dict.code&gt;selected&lt;/#if&gt;&gt;
                            ${r'${dict.value}'}&lt;#if show_code&gt;[${r'${dict.code}'}]&lt;/#if&gt;
                        &lt;/option&gt;
                    &lt;/#list&gt;
                &lt;/#if&gt;
            &lt;/#if&gt;
            &lt;#nested /&gt;
        &lt;/select&gt;
        &lt;#if chosen&gt;
            &lt;script&gt;
                $('#${r'${id}'}').chosen({
                    allow_single_deselect: true,
                    disable_search_threshold: 10,
                    no_results_text: "没有匹配的结果",
                    placeholder_text: "请选择一项"
                });
            &lt;/script&gt;
        &lt;/#if&gt;
    &lt;/div&gt;
&lt;/div&gt;
&lt;/#macro&gt;
        </@c.code>
    </@c.modal>
</@c.widget>

<@c.widget title="搜索表单实例">
    <@c.form class="col-xs-12 fa-border radius-base">
        <@c.input name="query.username" label="用户名" inline=true/>
        <@c.select name="query.type" label="偏好类型" inline=true enum_key="preferenceType"/>
        <@c.input name="query.name" label="偏好名称" inline=true/>
        <@c.input name="query.beginDate" label="创建开始日期" inline=true class="date-picker" readonly=true/>
        <@c.input name="query.endDate" label="创建结束日期" inline=true class="date-picker" readonly=true/>
        <@c.form_actions background=false>
            <@c.link name="查询" class="btn btn-sm btn-purple" icon="fa-search" type="submit" table_id="table"/>
            <@c.link name="清除" class="btn btn-sm btn-warning" icon="fa-undo" type="reset"/>
        </@c.form_actions>
    </@c.form>

    <@c.code>
&lt;@c.form class="col-xs-12 fa-border radius-base"&gt;
    &lt;@c.input name="query.username" label="用户名" inline=true/&gt;
    &lt;@c.select name="query.type" label="偏好类型" inline=true enum_key="preferenceType"/&gt;
    &lt;@c.input name="query.name" label="偏好名称" inline=true/&gt;
    &lt;@c.input name="query.beginDate" label="创建开始日期" inline=true class="date-picker" readonly=true/&gt;
    &lt;@c.input name="query.endDate" label="创建结束日期" inline=true class="date-picker" readonly=true/&gt;
    &lt;@c.form_actions background=false&gt;
        &lt;@c.link name="查询" class="btn btn-sm btn-purple" icon="fa-search" type="submit" table_id="table"/&gt;
        &lt;@c.link name="清除" class="btn btn-sm btn-warning" icon="fa-undo" type="reset"/&gt;
    &lt;/@c.form_actions&gt;
&lt;/@c.form&gt;
    </@c.code>
</@c.widget>

<@c.widget title="普通表单实例">
    <@c.form id="help-form" action="${ctx}/dashboard/help/save" multipart=true>
        <@c.input name="username" value="xxx" label="用户名" readonly=true/>
        <@c.input name="realname" label="真实姓名" required=true valid={"rangelength": "[1, 32]"}/>

        <@c.form_actions>
            <@c.button name="提交" class="btn-success" icon="fa-check"/>
        </@c.form_actions>
    </@c.form>

    <script>
        var $form = $('#help-form');
        var $btn = $form.find("button");

        $form.validate({
            submitHandler: function (form, event) {
                event.preventDefault();
                $btn.button('loading');
                formSubmit($(form), $btn);
            }
        });
    </script>

    <@c.code>
&lt;@c.form id="help-form" action="${r'${ctx}'}/dashboard/help/save" multipart=true&gt;
    &lt;@c.input name="username" value="xxx" label="用户名" readonly=true/&gt;
    &lt;@c.input name="realname" label="真实姓名" required=true valid={"rangelength": "[1, 32]"}/&gt;

    &lt;@c.form_actions&gt;
        &lt;@c.button name="提交" class="btn-success" icon="fa-check"/&gt;
    &lt;/@c.form_actions&gt;
&lt;/@c.form&gt;

&lt;script&gt;
    var $form = $('#help-form');
    var $btn = $form.find("button");

    $form.validate({
        submitHandler: function (form, event) {
            event.preventDefault();
            $btn.button('loading');
            formSubmit($(form), $btn);
        }
    });
&lt;/script&gt;
    </@c.code>
</@c.widget>