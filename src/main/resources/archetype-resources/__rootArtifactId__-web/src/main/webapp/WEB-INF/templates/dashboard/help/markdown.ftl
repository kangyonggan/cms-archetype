<@c.widget title="Markdown编辑器实例">
    <@c.textarea name="content" label="内容" type="markdown"></@c.textarea>

    <div style="clear: both">
        <@c.code>&lt;@c.textarea name="content" label="内容" type="markdown"&gt;&lt;/@c.textarea&gt;
        </@c.code>

        <@c.alert type="danger">
    此例子样式之所以看起来是有问题的，是因为这个编辑器被包在了<code>widget</code>组件中。
        </@c.alert>
    </div>
</@c.widget>


