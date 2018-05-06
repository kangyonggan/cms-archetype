#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.freemarker;

import ${groupId}.app.freemarker.AbstractFunctionTag;
import ${package}.constants.AppEnumHandle;
import ${groupId}.extra.core.model.EnumInfo;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author kangyonggan
 * @since 5/4/18
 */
@Component
public class EnumTag extends AbstractFunctionTag {

    /**
     * 获取枚举的名值对, 根据key
     *
     * @param arguments 参数
     * @return 返回枚举的所有名值对
     */
    public LinkedHashMap<Object, Object> map(List arguments) {
        if (!hasLessTwoArgs(arguments)) {
            throw new RuntimeException("获取枚举的名值对时必须指定枚举的key！");
        }
        String key = arguments.get(1).toString();
        EnumInfo enumInfo = AppEnumHandle.getEnumInfo(key);
        if (enumInfo == null) {
            throw new RuntimeException("获取枚举的名值对时异常，key=" + key + "不存在！");
        }

        try {
            return enumInfo.map();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
