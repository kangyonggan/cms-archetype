#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import ${groupId}.app.bean.Params;
import ${package}.model.Preference;

import java.util.List;

/**
 * @author kangyonggan
 * @since 5/4/18
 */
public interface PreferenceService {

    /**
     * 根据偏好类型、偏好名称和用户名查找偏好
     *
     * @param type     偏好类型
     * @param name     偏好名称
     * @param username 用户名
     * @return 返回偏好
     */
    Preference findPreferenceByTypeNameAndUsername(String type, String name, String username);

    /**
     * 搜索偏好
     *
     * @param params 参数
     * @return 返回符合条件的偏好
     */
    List<Preference> searchPreferences(Params params);

    /**
     * 查找偏好
     *
     * @param id 偏好ID
     * @return 返回偏好
     */
    Preference findPreferenceById(Long id);

    /**
     * 更新偏好
     *
     * @param preference 偏好
     */
    void updatePreference(Preference preference);

    /**
     * 删除偏好
     *
     * @param id 偏好ID
     */
    void deletePreferenceById(Long id);

    /**
     * 更新或者保存偏好
     *
     * @param username 用户名
     * @param type     偏好类型
     * @param names    偏好名称
     * @param value    偏好的值
     */
    void updateOrSavePreferences(String username, String type, String names, String value);

    /**
     * 批量删除
     *
     * @param ids 偏好ID
     */
    void deletePreferenceByIds(String ids);
}
