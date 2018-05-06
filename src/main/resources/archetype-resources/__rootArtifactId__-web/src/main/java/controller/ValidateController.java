#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;

import ${package}.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author kangyonggan
 * @since 5/4/18
 */
@Controller
@RequestMapping("validate")
public class ValidateController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private DictionaryTypeService dictionaryTypeService;

    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 校验用户名是否可用
     *
     * @param username    新用户名
     * @param oldUsername 老用户名
     * @return 如果用户名可用返回true，否则返回false
     */
    @RequestMapping(value = "user", method = RequestMethod.POST)
    @ResponseBody
    public boolean validateUsername(@RequestParam("username") String username,
                                    @RequestParam(value = "oldUsername", required = false, defaultValue = "") String oldUsername) {
        if (username.equals(oldUsername)) {
            return true;
        }

        return !userService.existsUsername(username);
    }

    /**
     * 校验角色代码是否可用
     *
     * @param code    新角色代码
     * @param oldCode 老角色代码
     * @return 如果角色代码可用返回true，否则返回false
     */
    @RequestMapping(value = "role", method = RequestMethod.POST)
    @ResponseBody
    public boolean validateRoleCode(@RequestParam("code") String code,
                                    @RequestParam(value = "oldCode", required = false, defaultValue = "") String oldCode) {
        if (code.equals(oldCode)) {
            return true;
        }

        return !roleService.existsRoleCode(code);
    }

    /**
     * 校验菜单代码是否可用
     *
     * @param code    新菜单代码
     * @param oldCode 老菜单代码
     * @return 如果菜单代码可用返回true，否则返回false
     */
    @RequestMapping(value = "menu", method = RequestMethod.POST)
    @ResponseBody
    public boolean validateMenuCode(@RequestParam("code") String code,
                                    @RequestParam(value = "oldCode", required = false, defaultValue = "") String oldCode) {
        if (code.equals(oldCode)) {
            return true;
        }

        return !menuService.existsMenuCode(code);
    }

    /**
     * 校验字典类型是否可用
     *
     * @param type    新字典类型
     * @param oldType 老字典类型
     * @return 如果字典类型可用返回true，否则返回false
     */
    @RequestMapping(value = "dictType", method = RequestMethod.POST)
    @ResponseBody
    public boolean validateDictType(@RequestParam("type") String type,
                                    @RequestParam(value = "oldType", required = false, defaultValue = "") String oldType) {
        if (type.equals(oldType)) {
            return true;
        }

        return !dictionaryTypeService.existsDictionaryType(type);
    }

    /**
     * 校验字典是否可用
     *
     * @param type    新字典类型
     * @param oldType 老字典类型
     * @param code    新字典代码
     * @param oldCode 老字典代码
     * @return 如果字典可用返回true，否则返回false
     */
    @RequestMapping(value = "dict", method = RequestMethod.POST)
    @ResponseBody
    public boolean validateDict(@RequestParam("type") String type,
                                @RequestParam(value = "oldType", required = false, defaultValue = "") String oldType,
                                @RequestParam("code") String code,
                                @RequestParam(value = "oldCode", required = false, defaultValue = "") String oldCode) {
        if (type.equals(oldType) && code.equals(oldCode)) {
            return true;
        }

        return !dictionaryService.existsDictionary(type, code);
    }

}