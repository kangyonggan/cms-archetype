#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller.web;

import ${groupId}.app.bean.Response;
import ${groupId}.app.constants.AppConstants;
import ${groupId}.app.controller.BaseController;
import ${groupId}.app.util.IpUtil;
import ${package}.model.User;
import ${package}.service.LoginLogService;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author kangyonggan
 * @since 5/4/18
 */
@Controller
@RequestMapping("auth")
@Log4j2
public class AuthController extends BaseController {

    @Autowired
    private LoginLogService loginLogService;

    /**
     * 登录模板
     *
     * @return 返回登录模板
     */
    @RequestMapping(method = RequestMethod.GET)
    public String layout() {
        return getPathRoot() + "/auth-layout";
    }

    /**
     * 登录界面
     *
     * @return 返回登录界面
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index() {
        return getPathRoot() + "/index";
    }

    /**
     * 登录
     *
     * @param user    用户
     * @param captcha 验证码
     * @param request 请求
     * @return 响应
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public Response login(@RequestParam(value = "captcha") String captcha, User user, HttpServletRequest request) {
        Response response = Response.getSuccessResponse();

        HttpSession session = request.getSession();
        String realCaptcha = (String) session.getAttribute(AppConstants.KEY_CAPTCHA);
        log.info("session中的验证码为：{}", realCaptcha);
        log.info("上送的验证码为：{}", captcha);

        if (!captcha.equalsIgnoreCase(realCaptcha)) {
            return response.failure("验证码错误或已失效");
        }

        // 清除验证码
        session.removeAttribute(AppConstants.KEY_CAPTCHA);

        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        final Subject subject = SecurityUtils.getSubject();

        try {
            // 30天
            session.setMaxInactiveInterval(30 * 24 * 60 * 60);
            subject.login(token);
        } catch (UnknownAccountException uae) {
            log.warn("用户名不存在", uae);
            return response.failure("用户名不存在");
        } catch (IncorrectCredentialsException ice) {
            log.warn("密码错误", ice);
            return response.failure("密码错误");
        } catch (DisabledAccountException dae) {
            log.warn("账号已禁用", dae);
            return response.failure("账号已禁用, 请联系管理员");
        } catch (Exception e) {
            log.error("未知异常", e);
            return response.failure();
        }

        // 保存登录日志
        loginLogService.saveLoginLog(user.getUsername(), IpUtil.getIp(request));
        return response;
    }

    /**
     * 注销
     *
     * @return 重定向到登录界面
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout() {
        final Subject subject = SecurityUtils.getSubject();
        log.info("logout {}", subject.getPrincipal());
        subject.logout();
        return "redirect:/auth";
    }

}
