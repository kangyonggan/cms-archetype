#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller.dashboard.system;

import ${groupId}.app.annotation.Token;
import ${groupId}.app.controller.BaseController;
import ${groupId}.app.bean.Page;
import ${groupId}.app.bean.Params;
import ${groupId}.app.bean.Response;
import ${package}.model.Preference;
import ${package}.service.PreferenceService;
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
@RequestMapping("dashboard/system/preference")
public class DashboardSystemPreferenceController extends BaseController {

    @Autowired
    private PreferenceService preferenceService;

    /**
     * 偏好管理
     *
     * @return 返回偏好管理界面
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_PREFERENCE")
    public String index() {
        return getPathList();
    }

    /**
     * 偏好列表查询
     *
     * @return 返回查询结果集
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_PREFERENCE")
    @ResponseBody
    public Page<Preference> list() {
        Params params = getRequestParams();
        List<Preference> preferences = preferenceService.searchPreferences(params);

        return new Page<>(preferences);
    }

    /**
     * 编辑偏好
     *
     * @param id    偏好ID
     * @param model 数据
     * @return 返回编辑偏好模态框
     */
    @RequestMapping(value = "{id:[${symbol_escape}${symbol_escape}d]+}/edit", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_PREFERENCE")
    @Token(key = "editPreference")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("preference", preferenceService.findPreferenceById(id));
        return getPathFormModal();
    }

    /**
     * 更新偏好
     *
     * @param preference 偏好
     * @param result     绑定结果
     * @return 响应
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("SYSTEM_PREFERENCE")
    @Token(key = "editPreference", type = Token.Type.CHECK)
    public Response update(@ModelAttribute("preference") @Valid Preference preference, BindingResult result) {
        Response response = Response.getSuccessResponse();
        if (!result.hasErrors()) {
            preferenceService.updatePreference(preference);
        } else {
            response.failure();
        }

        return response;
    }

    /**
     * 物理删除
     *
     * @param id 偏好ID
     * @return 响应
     */
    @RequestMapping(value = "{id:[${symbol_escape}${symbol_escape}d]+}/remove", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_PREFERENCE")
    @ResponseBody
    public Response remove(@PathVariable("id") Long id) {
        preferenceService.deletePreferenceById(id);
        return Response.getSuccessResponse();
    }

    /**
     * 批量删除
     *
     * @param ids 偏好ID
     * @return 响应
     */
    @RequestMapping(value = "deleted", method = RequestMethod.GET)
    @RequiresPermissions("SYSTEM_PREFERENCE")
    @ResponseBody
    public Response deleted(@RequestParam("ids") String ids) {
        preferenceService.deletePreferenceByIds(ids);
        return Response.getSuccessResponse();
    }

}
