#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import ${package}.model.Menu;

import java.util.List;

/**
 * @author kangyonggan
 * @since 5/4/18
 */
public interface MenuService {

    /**
     * 查找用户菜单
     *
     * @param username 用户名
     * @return 返回用户菜单
     */
    List<Menu> findMenusByUsername(String username);

    /**
     * 校验菜单代码是否存在
     *
     * @param code 菜单代码
     * @return 如果菜单代码存在则返回true，否则返回false
     */
    boolean existsMenuCode(String code);

    /**
     * 查找角色菜单
     *
     * @param code 角色代码
     * @return 返回角色菜单
     */
    List<Menu> findMenus4Role(String code);

    /**
     * 查找所有菜单
     *
     * @return 返回所有菜单
     */
    List<Menu> findAllMenus();

    /**
     * 查找菜单
     *
     * @param code 菜单代码
     * @return 返回菜单
     */
    Menu findMenuByCode(String code);

    /**
     * 保存菜单
     *
     * @param menu 菜单
     */
    void saveMenu(Menu menu);

    /**
     * 查找菜单
     *
     * @param id 菜单ID
     * @return 返回菜单
     */
    Menu findMenuById(Long id);

    /**
     * 更新菜单
     *
     * @param menu 菜单
     */
    void updateMenu(Menu menu);

    /**
     * 删除菜单
     *
     * @param menu 菜单
     */
    void deleteMenu(Menu menu);
}
