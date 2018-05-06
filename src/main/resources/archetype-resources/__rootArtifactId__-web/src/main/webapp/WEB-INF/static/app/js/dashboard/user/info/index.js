$(function () {
    var $form = $('#form');
    var $btn = $form.find("button");

    $form.validate({
        submitHandler: function (form, event) {
            event.preventDefault();
            $btn.button('loading');
            formSubmit($(form), $btn, function (response) {
                var user = response.user;
                $("#navFullname").html(user.realname);
                $form.find("input[type=password]").val("");
                $("#userAvatar").attr("src", ctx + user.avatar);
            });
        }
    });

    var file_input = $form.find('input[type=file]');
    file_input.ace_file_input({
        style: 'well',
        btn_choose: '点击这里添加图片',
        btn_change: null,
        no_icon: 'ace-icon fa fa-picture-o',
        droppable: false,
        allowExt: ["jpeg", "jpg", "png", "gif"],
        allowMime: ["image/jpeg", "image/jpg", "image/png", "image/gif"],
        maxSize: 10485760,//bytes
        thumbnail: 'fit'
    });

    file_input.on('file.error.ace', function(event, info) {
        if(info.error_count['size']) Message.warning('超出最大上传限制。');
        if(info.error_count['ext'] || info.error_count['mime']) Message.warning('不合法的文件类型。');
        event.preventDefault();
    });
});

