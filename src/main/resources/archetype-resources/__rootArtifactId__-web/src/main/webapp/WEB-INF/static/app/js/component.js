$(function () {

    // 日期插件汉化
    $.fn.datepicker.dates['zh-CN'] = {
        days: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"],
        daysShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六", "周日"],
        daysMin: ["日", "一", "二", "三", "四", "五", "六", "日"],
        months: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
        monthsShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
        today: "今天",
        suffix: [],
        meridiem: ["上午", "下午"]
    };

    // 日期插件通用配置
    $.fn.datepicker.defaults.language = "zh-CN";
    $.fn.datepicker.defaults.autoclose = true;
    $.fn.datepicker.defaults.todayHighlight = true;
    $.fn.datepicker.defaults.format = "yyyy-mm-dd";

    /**
     * 日期时间格式化
     *
     * @param fmt
     * @returns {*}
     */
    Date.prototype.format = function (fmt) {
        var o = {
            "M+": this.getMonth() + 1,                 //月份
            "d+": this.getDate(),                    //日
            "H+": this.getHours(),                   //小时
            "m+": this.getMinutes(),                 //分
            "s+": this.getSeconds(),                 //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S": this.getMilliseconds()             //毫秒
        };
        if (/(y+)/.test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        }
        for (var k in o) {
            if (new RegExp("(" + k + ")").test(fmt)) {
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            }
        }
        return fmt;
    };

    /**
     * art日期时间格式化
     */
    template.helper('datetimeFormat', function (date) {
        var d = new Date();
        d.setTime(date);
        return d.format("yyyy-MM-dd HH:mm:ss");
    });

    /**
     * art日期格式化
     */
    template.helper('dateFormat', function (date) {
        var d = new Date();
        d.setTime(date);
        return d.format("yyyy-MM-dd");
    });

    /**
     * art时间格式化
     */
    template.helper('timeFormat', function (date) {
        var d = new Date();
        d.setTime(date);
        return d.format("HH:mm:ss");
    });

    /**
     * art日期时间自定义格式化
     */
    template.helper('format', function (date, format) {
        var d = new Date();
        d.setTime(date);
        return d.format(format);
    });

    // 有滚动条时才显示回到顶部按钮
    window.onscroll = function () {
        if (document.documentElement.scrollTop + document.body.scrollTop > 100) {
            document.getElementById("btn-scroll-up").style.display = "block";
        } else {
            document.getElementById("btn-scroll-up").style.display = "none";
        }
    };

    /**
     * 序列化表单
     */
    $.fn.serializeForm = function () {
        var json = {};
        var arr = this.serializeArray();
        $.each(arr, function () {
            if (json[this.name]) {
                if (!json[this.name].push) {
                    json[this.name] = [json[this.name]];
                }
                json[this.name].push(this.value || '');
            } else {
                json[this.name] = this.value || '';
            }
        });

        return json;
    };

    // 提示框
    $.messager.model = {
        cancel: {text: "<i class='ace-icon fa fa-times'></i>取消", classed: "btn-default"},
        ok: {text: "<i class='ace-icon fa fa-check'></i>确定"}
    };

    // 关闭时清除模态框的数据
    $(document).on('hidden.bs.modal', '.modal', function () {
        if ($(this).attr("id") && $(this).attr("id").indexOf("tempModal") > -1) {
            return;
        }
        $(this).find(".modal-header h3").html("正在加载...");
        $(this).find(".modal-body").html("正在加载...");
        $(this).removeData('bs.modal');
    });

    // 让chosen select支持响应式
    $(window).on('resize.chosen', function () {
        var w = $('.chosen-select').parent().width();
        $('.chosen-select').siblings('.chosen-container').css({'width': w});
    }).triggerHandler('resize.chosen');

    // 让chosen select支持触摸屏
    if(!ace.vars['touch']) {
        $('.chosen-select').chosen();
    }
});

// 提示信息
var last_gritter;
var showMessage = function (type, message) {
    if (last_gritter) {
        $.gritter.remove(last_gritter);
    }
    last_gritter = $.gritter.add({
        title: '消息',
        text: message,
        time: 1500,
        class_name: type
    });
};

var Message = {
    success: function (message) {
        showMessage('gritter-success', message);
    },

    warning: function (message) {
        showMessage('gritter-warning', message);
    },

    error: function (message) {
        showMessage('gritter-error', message);
    },

    info: function (message) {
        showMessage('gritter-info', message);
    }
};

/**
 * 更新菜单激活状态
 *
 * @param hash
 */
function updateMenuActive(hash) {
    //  当前菜单
    var $menu = $($('a[data-url="' + hash + '"]')[0]).parent("li");

    // 所有菜单
    var $all_menus = $menu.parents("ul.nav-list").find("li");

    // 清除所有菜单状态
    $all_menus.removeClass("open");
    $all_menus.removeClass("active");
    $(".submenu").css("display", "");

    // 父菜单
    var $parent = $menu.parents("li");
    if ($parent.length > 0) {
        $parent.addClass("open active");
    }
    $menu.addClass("active");
}

/**
 * 更新面包屑
 *
 * @param hash
 */
function updateBreadcrumbs(hash) {
    var $menu = $('a[data-url="' + hash + '"]');

    // 下面这坨代码摘自ace.ajax-content.js
    var $breadcrumbs = $('.breadcrumb');
    if ($breadcrumbs.length > 0 && $breadcrumbs.is(':visible')) {
        $breadcrumbs.find('> li:not(:first-child)').remove();

        var i = 0;
        $menu.parents('.nav li').each(function () {
            var $link = $(this).find('> a');

            var $link_clone = $link.clone();
            $link_clone.find('i,.fa,.glyphicon,.ace-icon,.menu-icon,.badge,.label').remove();
            var text = $link_clone.text();
            $link_clone.remove();

            var href = $link.attr('href');

            if (i == 0) {
                var li = $('<li class="active"></li>').appendTo($breadcrumbs);
                li.text(text);
            } else {
                var li = $('<li><a /></li>').insertAfter($breadcrumbs.find('> li:first-child'));
                li.find('a').attr('href', href).text(text);
            }
            i++;
        })
    }
}

/**
 * 更新标题
 *
 * @param hash
 */
function updateTitle(hash) {
    var $menu = $($('a[data-url="' + hash + '"]')[0]);
    var title = $.trim($menu.text());

    if (title != '') {
        document.title = title;
    }
}

/**
 * 更新状态
 *
 * @param hash
 */
function updateState(hash) {
    updateBreadcrumbs(hash);
    updateMenuActive(hash);
    updateTitle(hash);
}

/**
 * 更新偏好
 *
 * @param type
 * @param names
 * @param value
 * @param callback
 */
function updatePreference(type, names, value, callback) {
    $.get(ctx + "/dashboard/preference/update", {
        type: type,
        names: names,
        value: value
    }, function (data, status) {
        if (status == "success") {
            data = eval('(' + data + ')');
            if ("0000" == data.respCo) {
                if (callback) {
                    callback();
                }
            } else {
                Message.error(data.respMsg);
            }
        } else {
            Message.error("服务器内部错误，请稍后再试。");
        }
    });
}

/**
 * 创建富文本框
 *
 * @param kedit
 */
function kedit(kedit){
    return KindEditor.create(kedit,{
        uploadJson: ctx + '/file/editor',
        fileManagerJson: ctx + '/file/manager'
    });
}