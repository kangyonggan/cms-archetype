$(function () {
    var $modal = $('.modal');
    var $form = $modal.find("form");
    var $btn = $modal.find("button[data-type=submit]");

    $form.validate({
        rules: {
            code: {
                remote: {
                    url: "/validate/menu",
                    type: 'post',
                    data: {
                        'code': function () {
                            return $('#code').val()
                        },
                        'oldCode': function () {
                            return $('#old-code').val();
                        }
                    }
                }
            }
        },
        submitHandler: function (form, event) {
            event.preventDefault();
            $btn.button('loading');

            formSubmit($(form), $btn, function () {
                window.location.hash = "system/menu?r=" + Math.random();
                $modal.modal('hide');
            });
        }
    });


});