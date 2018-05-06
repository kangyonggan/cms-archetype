#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.freemarker;

import ${groupId}.app.freemarker.AbstractFunctionTag;
import ${groupId}.app.util.ShiroUtils;
import ${package}.model.Preference;
import ${package}.service.PreferenceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

/**
 * @author kangyonggan
 * @since 5/4/18
 */
@Component
public class AppTag extends AbstractFunctionTag {

    @Autowired
    private PreferenceService preferenceService;

    /**
     * 获取UUID
     *
     * @param arguments 参数
     * @return 返回UUID
     */
    public String uuid(List arguments) {
        if (!hasLessTwoArgs(arguments)) {
            throw new RuntimeException("获取UUID, 时没有指定前缀");
        }
        String prefix = arguments.get(1).toString();
        return prefix + UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 获取偏好
     *
     * @param arguments 参数
     * @return 返回偏好
     */
    public String preference(List arguments) {
        String username = ShiroUtils.getShiroUsername();
        if (StringUtils.isEmpty(username)) {
            return StringUtils.EMPTY;
        }
        if (!hasLessThreeArgs(arguments)) {
            throw new RuntimeException("获取偏好时必须指定type和name！");
        }
        String type = arguments.get(1).toString();
        String name = arguments.get(2).toString();
        String defaultValue = "";
        if (hasLessFourArgs(arguments)) {
            defaultValue = arguments.get(3).toString();
        }

        Preference preference = preferenceService.findPreferenceByTypeNameAndUsername(type, name, username);
        return preference == null ? defaultValue : preference.getValue();
    }

}
