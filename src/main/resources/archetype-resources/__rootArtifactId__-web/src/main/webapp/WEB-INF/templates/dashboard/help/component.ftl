<p>
    下面给出常见组件的使用说明，但本项目部仅限于下面几种组件，每个组件都有很多参数，每个参数都有各自的用途。
</p>
<@c.accordion id="componment-accordion">
    <@c.accordion_section title="超链接">
        <#include "link.ftl"/>
    </@c.accordion_section>
    <@c.accordion_section title="按钮">
        <#include "button.ftl"/>
    </@c.accordion_section>
    <@c.accordion_section title="表单">
        <#include "form.ftl"/>
    </@c.accordion_section>
    <@c.accordion_section title="表格">
        <#include "table.ftl"/>
    </@c.accordion_section>
    <@c.accordion_section title="Markdown编辑器">
        <#include "markdown.ftl"/>
    </@c.accordion_section>
    <@c.accordion_section title="富文本编辑器">
        <#include "kindeditor.ftl"/>
    </@c.accordion_section>
</@c.accordion>