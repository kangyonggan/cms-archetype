$(function () {
    // 异步加载界面
    var $ajaxContent = $(".page-content-area");
    $ajaxContent.ace_ajax({
        'default_url': '#index',
        'content_url': function (hash) {
            return window.location.protocol + "//" + window.location.hostname + ":" + window.location.port + window.location.pathname + "/" + hash;
        },
        'update_active': updateMenuActive,
        'update_breadcrumbs': updateBreadcrumbs,
        'update_title': updateTitle,
        'loading_text': '<span class="loading">正在加载, 请稍等...</span>'
    });

    // 监听异步加载失败事件
    $ajaxContent.on("ajaxloaderror", function (e, data) {
        window.location.href = ctx + '/dashboard#404';
    });

    // modal form 提交
    $('.modal').on('click', '[data-type=submit]', function (e) {
        e.preventDefault();
        $($(this).parents('.modal').find("form")).submit();
    });

    // 查询。<@c.link type="submit" table_id="xxx" .../>
    $(document).on("click", "[data-type='submit']", function (e) {
        e.preventDefault();
        var $this = $(this);
        var $table = $("#" + $this.data("table-id"));

        var params = $this.parents("form").serializeForm();
        $table.bootstrapTable("refresh", {query: params});
        return false;
    });

    // 清除。<@c.link type="reset" .../>
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

    // 弹确认框。<@c.link type="confirm" title="" .../>
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
});

/**
 * 提交表单
 *
 * @param $form 表单
 * @param $btn 提交按钮
 * @param callback 回调
 */
function formSubmit($form, $btn, callback) {
    $form.ajaxSubmit({
        dataType: 'json',
        success: function (response) {
            if (response.respCo == '0000') {
                Message.success(response.respMsg);
                if (callback) {
                    callback(response);
                }
            } else {
                Message.error(response.respMsg);
            }
            if ($btn) {
                $btn.button('reset');
            }
        },
        error: function () {
            Message.error("服务器内部错误，请稍后再试。");
            if ($btn) {
                $btn.button('reset');
            }
        }
    });
}