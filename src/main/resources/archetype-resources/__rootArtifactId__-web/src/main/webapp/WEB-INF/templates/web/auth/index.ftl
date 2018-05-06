<#assign ctx="${(rca.contextPath)!''}">

<div class="col-lg-4 col-lg-offset-4 col-md-6 col-sm-6 col-sm-offset-3 col-xs-12">
    <div class="space-20"></div>
    <div class="space-30"></div>
    <div id="login-box" class="login-box visible widget-box">
        <div class="widget-body">
            <div class="widget-main">
                <h4 class="header skin-color lighter bigger">
                    <i class="ace-icon fa fa-coffee skin-color"></i>
                    登录
                </h4>

                <div class="space-6"></div>

                <form id="login-form" action="${ctx}/auth/login" method="post" novalidate="novalidate">
                    <div>
                        <label for="username" class="required">用户名</label>
                        <div class="input-icon input-icon-right">
                            <input type="text" name="username" class="form-control"
                                   placeholder="8至20位的字母和数字的组合" autocomplete="off"/>
                            <i class="ace-icon fa fa-user"></i>
                        </div>
                    </div>

                    <div class="space space-8"></div>

                    <div>
                        <label for="password" class="required">密码</label>
                        <div class="input-icon input-icon-right">
                            <input type="password" name="password" class="form-control"
                                   placeholder="8至20位的字母和数字的组合" autocomplete="off">
                            <i class="ace-icon fa fa-key"></i>
                        </div>
                    </div>

                    <div class="space space-8"></div>

                    <div>
                        <label for="captcha" class="required">验证码</label>
                        <div class="input-icon input-icon-right">
                            <input type="text" id="captcha" name="captcha" class="col-xs-6" placeholder="请输入4位数字的验证码"
                                   autocomplete="off">
                            <img id="captchaCode" onclick="this.src='${ctx}/captcha?'+Math.random();"
                                 src="${ctx}/captcha">
                        </div>
                    </div>

                    <div class="space-14"></div>

                    <div class="clearfix">
                        <button type="reset" class="width-30 pull-left btn btn-sm">
                            <i class="ace-icon fa fa-refresh"></i>
                            重置
                        </button>
                        <button id="submit" class="width-35 pull-right btn btn-sm btn-primary"
                                data-loading-text="登录中...">
                            <i class="ace-icon fa fa-key"></i>
                            登录
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="${ctx}/static/app/js/web/auth/index.js"></script>