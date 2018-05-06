#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;


import ${groupId}.app.bean.Params;
import ${package}.model.User;

import java.util.List;

/**
 * @author kangyonggan
 * @since 5/4/18
 */
public interface UserService {

    /**
     * 根据用户名查找用户
     *
     * @param username 用户名
     * @return 返回用户
     */
    User findUserByUsername(String username);

    /**
     * 搜索用户
     *
     * @param params 参数
     * @return 返回符合条件的用户
     */
    List<User> searchUsers(Params params);

    /**
     * 更新用户
     *
     * @param user 用户
     */
    void updateUserByUsername(User user);

    /**
     * 校验用户名是否存在
     *
     * @param username 用户名
     * @return 如果用户名存在返回true，否则返回false
     */
    boolean existsUsername(String username);

    /**
     * 保存用户
     *
     * @param user 用户
     */
    void saveUserWithDefaultRole(User user);

    /**
     * 更新用户密码
     *
     * @param user 用户
     */
    void updateUserPassword(User user);

    /**
     * 删除用户
     *
     * @param username 用户名
     */
    void deleteUserByUsername(String username);

    /**
     * 更新用户角色
     *
     * @param username 用户名
     * @param roles    角色代码
     */
    void updateUserRoles(String username, String roles);
}
