<div class="ace-settings-container" id="ace-settings-container">
    <div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
        <i class="ace-icon fa fa-cog bigger-130"></i>
    </div>

    <div class="ace-settings-box clearfix" id="ace-settings-box">
        <div class="pull-left width-50">
            <div class="ace-settings-item">
                <div class="pull-left">
                    <select id="skin-colorpicker" class="hide">
                        <option data-skin="no-skin" value="#438EB9">#438EB9</option>
                        <option data-skin="skin-1" value="#222A2D">#222A2D</option>
                        <option data-skin="skin-2" value="#C6487E">#C6487E</option>
                        <option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
                    </select>
                </div>
                <span>&nbsp; 选择皮肤</span>
            </div>
            <div class="ace-settings-item">
                <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-navbar"/>
                <label class="lbl" for="ace-settings-navbar"> 固定导航条</label>
            </div>

            <div class="ace-settings-item">
                <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-sidebar"/>
                <label class="lbl" for="ace-settings-sidebar"> 固定侧栏</label>
            </div>

            <div class="ace-settings-item">
                <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-breadcrumbs"/>
                <label class="lbl" for="ace-settings-breadcrumbs"> 固定面包屑</label>
            </div>
        </div>

        <div class="pull-left width-50">
            <div class="ace-settings-item">
                <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-hover"/>
                <label class="lbl" for="ace-settings-hover"> 悬浮子菜单</label>
            </div>

            <div class="ace-settings-item">
                <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-compact"/>
                <label class="lbl" for="ace-settings-compact"> 简洁侧栏</label>
            </div>

            <div class="ace-settings-item">
                <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-highlight"/>
                <label class="lbl" for="ace-settings-highlight"> 侧栏高亮</label>
            </div>

            <div class="ace-settings-item">
                <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-add-container"/>
                <label class="lbl" for="ace-settings-add-container"> 内容器</label>
            </div>
        </div>
    </div>
</div>

<script>
    $(function () {
        var skins = {"#438EB9": "no-skin", "#222A2D": "skin-1", "#C6487E": "skin-2", "#D0D0D0": "skin-3"};

        // 初始化皮肤
        var skin = $("body").attr("class");
        var color = findColor(skin);
        $(".ace-settings-item .btn-colorpicker").css("background", color);
        $(".ace-settings-item .dropdown-colorpicker li a").removeClass("selected");
        var as = $(".ace-settings-item .dropdown-colorpicker li a");
        for (var i = 0; i < as.length; i++) {
            if ($(as[i]).data("color") == color) {
                $(as[i]).addClass("selected");
                break;
            }
        }

        // 初始化navbar
        ace.settings.navbar_fixed(null, ${app('preference', 'ace', 'fixed-navbar', "false")}, false);
        // 初始化sidebar
        ace.settings.sidebar_fixed(null, ${app('preference', 'ace', 'fixed-sidebar', "false")}, false);
        // 初始化breadcrumbs
        ace.settings.breadcrumbs_fixed(null, ${app('preference', 'ace', 'fixed-breadcrumbs', "false")}, false);
        // 初始化container
        document.getElementById("ace-settings-container").checked = ${app('preference', 'ace', 'container', "false")};
        // 初始化hover
        document.getElementById("ace-settings-hover").checked = ${app('preference', 'ace', 'hover', "false")};
        // 初始化compact
        document.getElementById("ace-settings-compact").checked = ${app('preference', 'ace', 'compact', "false")};
        // 初始化highlight
        document.getElementById("ace-settings-highlight").checked = ${app('preference', 'ace', 'highlight', "false")};

        // 切换皮肤
        $(".dropdown-colorpicker .colorpick-btn").click(function () {
            var color = $(this).data("color");
            var skin = skins[color];
            updatePreference("ace", "skin", skin);
        });

        /**
         * 查找皮肤颜色
         *
         * @param skin
         * @returns {*}
         */
        function findColor(skin) {
            for (var color in skins) {
                if (skins[color] == skin) {
                    return color;
                }
            }

            return "#438EB9";
        }

        // 固定导航栏
        $("#ace-settings-navbar").click(function () {
            var isChecked = $(this).is(":checked");
            if (isChecked) {
                updatePreference("ace", "fixed-navbar", isChecked);
            } else {
                updatePreference("ace", "fixed-navbar,fixed-sidebar,fixed-breadcrumbs", isChecked);
            }
        });
        // 固定侧栏
        $("#ace-settings-sidebar").click(function () {
            var isChecked = $(this).is(":checked");
            if (isChecked) {
                updatePreference("ace", "fixed-navbar,fixed-sidebar", isChecked);
            } else {
                updatePreference("ace", "fixed-sidebar,fixed-breadcrumbs", isChecked);
            }
        });
        // 固定面包屑
        $("#ace-settings-breadcrumbs").click(function () {
            var isChecked = $(this).is(":checked");
            if (isChecked) {
                updatePreference("ace", "fixed-navbar,fixed-sidebar,fixed-breadcrumbs", isChecked);
            } else {
                updatePreference("ace", "fixed-breadcrumbs", isChecked);
            }
        });
        // 内容器
        $("#ace-settings-add-container").click(function () {
            var isChecked = $(this).is(":checked");
            updatePreference("ace", "container", isChecked);
        });
        // 悬浮子菜单
        $("#ace-settings-hover").click(function () {
            var isChecked = $(this).is(":checked");
            if (isChecked) {
                updatePreference("ace", "hover", isChecked);
            } else {
                updatePreference("ace", "hover,compact", isChecked);

            }
        });
        // 简洁侧栏
        $("#ace-settings-compact").click(function () {
            var isChecked = $(this).is(":checked");
            if (isChecked) {
                updatePreference("ace", "hover,compact", isChecked);
            } else {
                updatePreference("ace", "compact", isChecked);
            }
        });
        // 侧栏高亮
        $("#ace-settings-highlight").click(function () {
            var isChecked = $(this).is(":checked");
            updatePreference("ace", "highlight", isChecked);
        });
    })
</script>