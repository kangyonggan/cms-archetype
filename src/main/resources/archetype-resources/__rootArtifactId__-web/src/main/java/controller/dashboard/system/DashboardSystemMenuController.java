#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller.dashboard.system;

import ${groupId}.app.annotation.Token;
import ${groupId}.app.controller.BaseController;
import ${groupId}.app.bean.Response;
import ${package}.model.Menu;
import ${package}.service.MenuService;
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
@RequestMapping("dashboard/system/menu")
public class DashboardSystemMenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    /**
     * 菜单管理
     *
     * @param model 数据
     * @return 返回菜单管理界面
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_MENU")
    public String index(Model model) {
        List<Menu> allMenus = menuService.findAllMenus();

        model.addAttribute("allMenus", allMenus);
        return getPathIndex();
    }

    /**
     * 添加菜单
     *
     * @param pcode 父菜单代码
     * @param model 数据
     * @return 返回添加菜单模态框
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_MENU")
    @Token(key = "createMenu")
    public String create(@RequestParam(value = "pcode", defaultValue = "") String pcode,
                         Model model) {
        model.addAttribute("menu", new Menu());
        model.addAttribute("parentMenu", menuService.findMenuByCode(pcode));
        return getPathFormModal();
    }

    /**
     * 保存菜单
     *
     * @param menu   菜单
     * @param result 绑定结果
     * @return 响应
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    @RequiresPermissions("SYSTEM_MENU")
    @ResponseBody
    @Token(key = "createMenu", type = Token.Type.CHECK)
    public Response save(@ModelAttribute("menu") @Valid Menu menu, BindingResult result) {
        Response response = Response.getSuccessResponse();
        if (!result.hasErrors()) {
            menuService.saveMenu(menu);
        } else {
            response.failure();
        }
        return response;
    }

    /**
     * 编辑菜单
     *
     * @param id    菜单ID
     * @param model 数据
     * @return 返回编辑菜单模态框
     */
    @RequestMapping(value = "{id:[${symbol_escape}${symbol_escape}d]+}/edit", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_MENU")
    @Token(key = "editMenu")
    public String edit(@PathVariable Long id, Model model) {
        Menu menu = menuService.findMenuById(id);

        model.addAttribute("menu", menu);
        model.addAttribute("parentMenu", menuService.findMenuByCode(menu.getPcode()));
        return getPathFormModal();
    }

    /**
     * 更新菜单
     *
     * @param menu   菜单
     * @param result 绑定结果
     * @return 响应
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @RequiresPermissions("SYSTEM_MENU")
    @ResponseBody
    @Token(key = "editMenu", type = Token.Type.CHECK)
    public Response update(@ModelAttribute("menu") @Valid Menu menu, BindingResult result) {
        Response response = Response.getSuccessResponse();

        if (!result.hasErrors()) {
            menuService.updateMenu(menu);
        } else {
            response.failure();
        }

        return response;
    }

    /**
     * 删除菜单
     *
     * @param menu 菜单
     * @return 响应
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @RequiresPermissions("SYSTEM_MENU")
    @ResponseBody
    public Response delete(@ModelAttribute("menu") Menu menu) {
        menuService.deleteMenu(menu);
        return Response.getSuccessResponse();
    }

}
