$(function () {
    document.title = "开发手册";

    // 所有菜单
    var $all_menus = $("ul.nav-list").find("li");
    // 清除所有菜单状态
    $all_menus.removeClass("open");
    $all_menus.removeClass("active");
    $(".submenu").css("display", "");
});