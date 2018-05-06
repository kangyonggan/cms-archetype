$(function () {
    window.document.title = "登录";
    var $form = $('#login-form');
    var $btn = $("#submit");

    $form.validate({
        rules: {
            username: {
                required: true,
                isUsername: true
            },
            password: {
                required: true,
                isPassword: true
            },
            captcha: {
                required: true,
                isCaptcha: true
            }
        },
        submitHandler: function () {
            $btn.button('loading');
            $form.ajaxSubmit({
                dataType: 'json',
                success: function (response) {
                    if (response.respCo == "0000") {
                        window.location.href = ctx + "/dashboard";
                    } else {
                        Message.error(response.respMsg);
                        $("#captcha").val("");
                        $("#captchaCode").attr("src", ctx + '/captcha?' + Math.random());
                    }
                    $btn.button('reset');
                },
                error: function () {
                    Message.error("服务器内部错误，请稍后再试。");
                    $btn.button('reset');
                }
            });
            return false;
        },
        errorPlacement: function (error, element) {
            error.appendTo(element.parent());
        },
        errorElement: "div",
        errorClass: "error"
    });
});

