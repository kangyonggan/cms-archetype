#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller.dashboard;

import ${groupId}.app.controller.BaseController;
import ${groupId}.app.bean.Response;
import ${groupId}.app.bean.ShiroUser;
import ${package}.model.Menu;
import ${package}.model.User;
import ${package}.service.MenuService;
import ${package}.service.UserService;
import ${groupId}.app.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author kangyonggan
 * @since 5/4/18
 */
@Controller
@RequestMapping("dashboard")
public class DashboardController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    /**
     * 工作台模板
     *
     * @return 返回工作台模板
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("DASHBOARD")
    public String layout(Model model) {
        ShiroUser shiroUser = ShiroUtils.getShiroUser();
        User user = userService.findUserByUsername(shiroUser.getUsername());
        List<Menu> menus = menuService.findMenusByUsername(shiroUser.getUsername());

        model.addAttribute("_user", user);
        model.addAttribute("_menus", menus);
        return getPathRoot() + "/layout";
    }

    /**
     * 工作台首页
     *
     * @return 返回工作台首页
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    @RequiresPermissions("DASHBOARD")
    public String index() {
        return getPathRoot() + "/index";
    }

    /**
     * 开发手册
     *
     * @return 返回开发手册界面
     */
    @RequestMapping(value = "help", method = RequestMethod.GET)
    @RequiresPermissions("DASHBOARD")
    public String help() {
        return getPathRoot() + "/help/index";
    }

    /**
     * 模态框示例
     *
     * @return 返回模态框
     */
    @RequestMapping(value = "help/create", method = RequestMethod.GET)
    @RequiresPermissions("DASHBOARD")
    public String create() {
        return getPathRoot() + "/help/help-modal";
    }

    /**
     * 模态框示例提交
     *
     * @return 响应
     */
    @RequestMapping(value = "help/save", method = RequestMethod.POST)
    @RequiresPermissions("DASHBOARD")
    @ResponseBody
    public Response save() {
        return Response.getSuccessResponse();
    }
}
