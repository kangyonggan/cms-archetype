#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import ${groupId}.app.bean.Params;
import ${package}.model.Role;

import java.util.List;

/**
 * @author kangyonggan
 * @since 5/4/18
 */
public interface RoleService {

    /**
     * 查找用户角色
     *
     * @param username 用户名
     * @return 返回用户的所有角色
     */
    List<Role> findRolesByUsername(String username);

    /**
     * 搜索角色
     *
     * @param params 参数
     * @return 返回符合条件的角色
     */
    List<Role> searchRoles(Params params);

    /**
     * 校验角色代码是否存在
     *
     * @param code 角色代码
     * @return 如果角色代码存在返回true，否则返回false
     */
    boolean existsRoleCode(String code);

    /**
     * 查找所有角色
     *
     * @return 返回所有角色
     */
    List<Role> findAllRoles();

    /**
     * 保存角色
     *
     * @param role 角色
     */
    void saveRole(Role role);

    /**
     * 查找角色
     *
     * @param code 角色代码
     * @return 返回角色
     */
    Role findRoleByCode(String code);

    /**
     * 更新角色
     *
     * @param role 角色
     */
    void updateRole(Role role);

    /**
     * 删除角色
     *
     * @param code 角色代码
     */
    void deleteRoleByCode(String code);

    /**
     * 更新角色菜单
     *
     * @param code  角色代码
     * @param menus 菜单代码
     */
    void updateRoleMenus(String code, String menus);

}
