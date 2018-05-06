#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller.dashboard;

import ${groupId}.app.controller.BaseController;
import ${groupId}.app.bean.Response;
import ${groupId}.app.util.ShiroUtils;
import ${package}.service.PreferenceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kangyonggan
 * @since 5/4/18
 */
@RestController
@RequestMapping("dashboard/preference")
public class DashboardPreferenceController extends BaseController {

    @Autowired
    private PreferenceService preferenceService;

    /**
     * 更新偏好
     *
     * @param type  偏好类型
     * @param names 偏好名称
     * @param value 偏好的值
     * @return 响应
     */
    @RequestMapping(value = "update", method = RequestMethod.GET)
    @RequiresPermissions("DASHBOARD")
    public Response update(@RequestParam("type") String type,
                           @RequestParam("names") String names,
                           @RequestParam("value") String value) {
        preferenceService.updateOrSavePreferences(ShiroUtils.getShiroUsername(), type, names, value);
        return Response.getSuccessResponse();
    }

}
