#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller.dashboard.system;

import ${groupId}.app.annotation.Token;
import ${groupId}.app.controller.BaseController;
import ${groupId}.app.bean.Page;
import ${groupId}.app.bean.Params;
import ${groupId}.app.bean.Response;
import ${package}.model.Role;
import ${package}.model.User;
import ${package}.service.RoleService;
import ${package}.service.UserService;
import ${groupId}.app.util.Collections3;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author kangyonggan
 * @since 5/4/18
 */
@Controller
@RequestMapping("dashboard/system/user")
public class DashboardSystemUserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * 用户管理
     *
     * @return 返回用户管理界面
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_USER")
    public String index() {
        return getPathList();
    }

    /**
     * 用户列表查询
     *
     * @return 返回查询结果集
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_USER")
    @ResponseBody
    public Page<User> list() {
        Params params = getRequestParams();
        List<User> users = userService.searchUsers(params);

        return new Page<>(users);
    }

    /**
     * 编辑用户
     *
     * @param username 用户名
     * @param model    数据
     * @return 返回编辑用户模态框
     */
    @RequestMapping(value = "{username:[${symbol_escape}${symbol_escape}w]+}/edit", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_USER")
    @Token(key = "editUser")
    public String edit(@PathVariable("username") String username, Model model) {
        model.addAttribute("user", userService.findUserByUsername(username));
        return getPathFormModal();
    }

    /**
     * 更新用户
     *
     * @param user   用户
     * @param result 绑定结果
     * @return 响应
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("SYSTEM_USER")
    @Token(key = "editUser", type = Token.Type.CHECK)
    public Response update(@ModelAttribute("user") @Valid User user, BindingResult result) {
        Response response = Response.getSuccessResponse();
        if (!result.hasErrors()) {
            userService.updateUserByUsername(user);
        } else {
            response.failure();
        }

        return response;
    }

    /**
     * 添加用户
     *
     * @param model 数据
     * @return 返回添加用户模态框
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_USER")
    @Token(key = "createUser")
    public String create(Model model) {
        model.addAttribute("user", new User());
        return getPathFormModal();
    }

    /**
     * 保存用户
     *
     * @param user   用户
     * @param result 绑定结果
     * @return 响应
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("SYSTEM_USER")
    @Token(key = "createUser", type = Token.Type.CHECK)
    public Response save(@ModelAttribute("user") @Valid User user, BindingResult result) {
        Response response = Response.getSuccessResponse();
        if (!result.hasErrors()) {
            userService.saveUserWithDefaultRole(user);
        } else {
            response.failure();
        }

        return response;
    }

    /**
     * 删除/恢复
     *
     * @param username  用户名
     * @param isDeleted 是否删除
     * @return 响应
     */
    @RequestMapping(value = "{username:[${symbol_escape}${symbol_escape}w]+}/deleted/{isDeleted:${symbol_escape}${symbol_escape}b0${symbol_escape}${symbol_escape}b|${symbol_escape}${symbol_escape}b1${symbol_escape}${symbol_escape}b}", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_USER")
    @ResponseBody
    public Response delete(@PathVariable("username") String username, @PathVariable("isDeleted") byte isDeleted) {
        User user = userService.findUserByUsername(username);
        user.setIsDeleted(isDeleted);
        userService.updateUserByUsername(user);
        return Response.getSuccessResponse();
    }

    /**
     * 物理删除
     *
     * @param username 用户名
     * @return 响应
     */
    @RequestMapping(value = "{username:[${symbol_escape}${symbol_escape}w]+}/remove", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_ROLE")
    @ResponseBody
    public Response remove(@PathVariable("username") String username) {
        userService.deleteUserByUsername(username);
        return Response.getSuccessResponse();
    }

    /**
     * 修改密码
     *
     * @param username 用户名
     * @param model    数据
     * @return 返回修改密码模态框
     */
    @RequestMapping(value = "{username:[${symbol_escape}${symbol_escape}w]+}/password", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_USER")
    @Token(key = "editPassword")
    public String password(@PathVariable("username") String username, Model model) {
        model.addAttribute("user", userService.findUserByUsername(username));
        return getPathRoot() + "/password-modal";
    }

    /**
     * 修改密码提交
     *
     * @param user   用户
     * @param result 绑定结果
     * @return 响应
     */
    @RequestMapping(value = "password", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("SYSTEM_USER")
    @Token(key = "editPassword", type = Token.Type.CHECK)
    public Response password(@ModelAttribute("user") @Valid User user, BindingResult result) {
        Response response = Response.getSuccessResponse();
        if (!result.hasErrors()) {
            userService.updateUserPassword(user);
        } else {
            response.failure();
        }

        return response;
    }

    /**
     * 设置角色
     *
     * @param username 用户名
     * @param model    数据
     * @return 返回设置角色模态框
     */
    @RequestMapping(value = "{username:[${symbol_escape}${symbol_escape}w]+}/roles", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_USER")
    @Token(key = "setRoles")
    public String roles(@PathVariable("username") String username, Model model) {
        List<Role> userRoles = roleService.findRolesByUsername(username);
        userRoles = Collections3.extractToList(userRoles, "code");
        List<Role> allRoles = roleService.findAllRoles();

        model.addAttribute("username", username);
        model.addAttribute("userRoles", userRoles);
        model.addAttribute("allRoles", allRoles);
        return getPathRoot() + "/roles-modal";
    }

    /**
     * 保存角色
     *
     * @param username 用户名
     * @param roles    角色代码
     * @return 响应
     */
    @RequestMapping(value = "{username:[${symbol_escape}${symbol_escape}w]+}/roles", method = RequestMethod.POST)
    @RequiresPermissions("SYSTEM_USER")
    @ResponseBody
    @Token(key = "setRoles", type = Token.Type.CHECK)
    public Response updateUserRoles(@PathVariable(value = "username") String username,
                                    @RequestParam(value = "roles", defaultValue = "") String roles) {
        User user = userService.findUserByUsername(username);
        userService.updateUserRoles(user.getUsername(), roles);

        return Response.getSuccessResponse();
    }
}
