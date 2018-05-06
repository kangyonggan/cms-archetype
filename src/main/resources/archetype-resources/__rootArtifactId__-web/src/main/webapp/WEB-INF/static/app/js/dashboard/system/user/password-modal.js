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