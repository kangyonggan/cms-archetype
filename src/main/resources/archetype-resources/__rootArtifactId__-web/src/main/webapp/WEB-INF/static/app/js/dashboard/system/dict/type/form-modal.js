$(function () {
    var $modal = $('.modal');
    var $form = $modal.find("form");
    var $btn = $modal.find("button[data-type=submit]");

    $form.validate({
        rules: {
            type: {
                remote: {
                    url: ctx + "/validate/dictType",
                    type: 'post',
                    data: {
                        'type': function () {
                            return $('#type').val()
                        },
                        'oldType': function () {
                            return $('#old-type').val();
                        }
                    }
                }
            }
        },
        submitHandler: function (form, event) {
            event.preventDefault();
            $btn.button('loading');
            formSubmit($(form), $btn, function () {
                $modal.modal('hide');
                var params = $("#form").serializeForm();
                $('#table').bootstrapTable("refresh", {query: params});
            });
        }
    });
});