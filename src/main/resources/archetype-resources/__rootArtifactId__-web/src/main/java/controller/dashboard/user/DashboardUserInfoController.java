#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller.dashboard.user;

import ${groupId}.app.controller.BaseController;
import ${groupId}.app.bean.Response;
import ${package}.model.User;
import ${package}.service.UserService;
import ${groupId}.app.util.FileUpload;
import ${groupId}.app.util.ShiroUtils;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

/**
 * @author kangyonggan
 * @since 5/4/18
 */
@Controller
@RequestMapping("dashboard/user/info")
public class DashboardUserInfoController extends BaseController {

    @Autowired
    private UserService userService;

    @Value("${symbol_dollar}{file.root.path}")
    private String fileRootPath;

    /**
     * 基本信息
     *
     * @param model 数据
     * @return 返回基本信息界面
     */
    @RequestMapping(method = RequestMethod.GET)
    @RequiresPermissions("USER_INFO")
    public String info(Model model) {
        User user = userService.findUserByUsername(ShiroUtils.getShiroUsername());

        model.addAttribute("user", user);
        return getPathIndex();
    }

    /**
     * 基本信息
     *
     * @param user   用户
     * @param result 绑定结果
     * @return 响应
     * @throws FileUploadException 文件上传异常
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("USER_INFO")
    public Response info(@ModelAttribute(value = "user") @Valid User user, BindingResult result,
                         @RequestParam(value = "file", required = false) MultipartFile file) throws FileUploadException {
        Response response = Response.getSuccessResponse();

        if (!result.hasErrors()) {
            user.setUsername(ShiroUtils.getShiroUsername());

            if (file != null && !file.isEmpty()) {
                String avatarPath = FileUpload.upload(fileRootPath, "upload/", file, "AVATAR");
                user.setAvatar(avatarPath);
            }

            if (StringUtils.isEmpty(user.getPassword())) {
                user.setPassword(null);
            }

            userService.updateUserByUsername(user);

            user = userService.findUserByUsername(user.getUsername());
            response.put("user", user);
        } else {
            response.failure();
        }

        return response;
    }

}
