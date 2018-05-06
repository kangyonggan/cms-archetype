#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.constants;

import ${groupId}.extra.core.annotation.Enum;
import lombok.Getter;

/**
 * @author kangyonggan
 * @since 5/4/18
 */
@Enum(code = "type", description = "偏好类型枚举")
public enum PreferenceType {

    /**
     * ace偏好
     */
    ACE("ace", "Ace Admin的偏好");

    /**
     * 偏好类型
     */
    @Getter
    private String type;

    /**
     * 偏好名称
     */
    @Getter
    private String name;

    PreferenceType(String type, String name) {
        this.type = type;
        this.name = name;
    }

}
