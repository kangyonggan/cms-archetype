#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.mapper;

import ${package}.model.Role;
import ${groupId}.app.mapper.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author kangyonggan
 * @since 5/4/18
 */
@Repository
public interface RoleMapper extends MyMapper<Role> {

    /**
     * 查找用户角色
     *
     * @param username 用户名
     * @return 返回用户所有角色
     */
    List<Role> selectRolesByUsername(@Param("username") String username);

    /**
     * 删除用户所有角色
     *
     * @param username 用户名
     */
    void deleteAllRolesByUsername(@Param("username") String username);

    /**
     * 删除角色菜单
     *
     * @param code 角色代码
     */
    void deleteRoleMenus(@Param("code") String code);

    /**
     * 插入角色菜单
     *
     * @param code      角色代码
     * @param menuCodes 菜单代码
     */
    void insertRoleMenus(@Param("code") String code, @Param("menuCodes") List<String> menuCodes);

}