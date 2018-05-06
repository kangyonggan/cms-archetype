<@c.widget title="宏定义">

    <p>
        按钮一般用在表单的提交按钮和取消按钮。
    </p>

    <@c.modal title="按钮的宏定义">
        <@c.code>
&lt;#macro button name id="" class="" icon="" dismiss=false type=""&gt;
&lt;button class="btn ${r'${class}'}" data-loading-text="正在${r'${name}'}..."
    &lt;#if id!=""&gt;
        id="${r'${id}'}"
    &lt;/#if&gt;
    &lt;#if dismiss&gt;
         data-dismiss="modal"
    &lt;/#if&gt;
    &lt;#if type!=''&gt;
        data-type="${r'${type}'}"
    &lt;/#if&gt;
&gt;
    &lt;#if icon != ''&gt;
    &lt;i class="ace-icon fa ${r'${icon}'}"&gt;&lt;/i&gt;
    &lt;/#if&gt;
    ${r'${name}'}
&lt;/button&gt;
&lt;/#macro&gt;
        </@c.code>
    </@c.modal>
</@c.widget>

<@c.widget title="基础实例">
    <@c.button name="取消" icon="fa-times" dismiss=true/>
    <@c.button name="提交" type="submit" class="btn-success" icon="fa-check"/>

    <@c.code>&lt;@c.button name="取消" icon="fa-times" dismiss=true/&gt;
&lt;@c.button name="提交" type="submit" class="btn-success" icon="fa-check"/&gt;
    </@c.code>
</@c.widget>

<@c.widget title="内置实例">
            <p>
                系统中内置了一个特殊功能的按钮：<code>提交</code>。
            </p>
            <p>
                <code>提交</code>会提交所在模态框的表单。注意：type必须指定为submit，且只能用于模态框的表单中。
                <@c.modal title="实现逻辑">
                    <@c.code>
$('.modal').on('click', '[data-type=submit]', function (e) {
    e.preventDefault();
    $($(this).parents('.modal').find("form")).submit();
});
                    </@c.code>
                </@c.modal>
            </p>

            <p>内置实例的应用：</p>

    <@c.link name="新增" class="btn btn-sm btn-skin" href="${ctx}/dashboard/help/create" modal="myModal"/>

    <@c.code>
&lt;#assign ctx="${r"${(rca.contextPath)!''}"}"&gt;
&lt;#assign modal_title="新增" /&gt;

&lt;@override name="modal-body"&gt;
    &lt;@c.form id="modal-form" action="${ctx}/dashboard/help/save"&gt;
        &lt;@c.input name="code" label="代码" required=true valid={"rangelength": "[1, 32]"}/&gt;
        &lt;@c.input name="name" label="名称"/&gt;
    &lt;/@c.form&gt;
&lt;/@override&gt;

&lt;@override name="modal-footer"&gt;
    &lt;@c.button name="取消" icon="fa-times" dismiss=true/&gt;
    &lt;@c.button name="提交" type="submit" class="btn-success" icon="fa-check"/&gt;
&lt;script&gt;
    $(function () {
        var $modal = $('.modal');
        var $form = $modal.find("form");
        var $btn = $modal.find("button[data-type=submit]");

        $form.validate({
            submitHandler: function (form, event) {
                event.preventDefault();
                $btn.button('loading');
                formSubmit($(form), $btn, function () {
                    $modal.modal('hide');
                });
            }
        });
    });
&lt;/script&gt;
&lt;/@override&gt;

&lt;@extends name="../../modal-layout.ftl"/&gt;
    </@c.code>
</@c.widget>