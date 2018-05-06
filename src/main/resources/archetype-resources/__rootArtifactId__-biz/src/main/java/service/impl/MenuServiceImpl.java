#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.impl;

import ${groupId}.app.constants.MonitorType;
import ${package}.mapper.MenuMapper;
import ${package}.model.Menu;
import ${package}.service.MenuService;
import ${groupId}.extra.core.annotation.Cache;
import ${groupId}.extra.core.annotation.CacheDel;
import ${groupId}.extra.core.annotation.Log;
import ${groupId}.extra.core.annotation.Monitor;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kangyonggan
 * @since 5/4/18
 */
@Service
public class MenuServiceImpl extends BaseService<Menu> implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    @Log
    @Cache(key = "menu:username:${symbol_dollar}{username}")
    public List<Menu> findMenusByUsername(String username) {
        List<Menu> menus = menuMapper.selectMenusByUsername(username);
        List<Menu> wrapList = new ArrayList();

        return recursionList(menus, wrapList, "", 0L);
    }

    @Override
    @Log
    public boolean existsMenuCode(String code) {
        Menu menu = new Menu();
        menu.setCode(code);

        return super.exists(menu);
    }

    @Override
    @Log
    @Cache(key = "menu:role:${symbol_dollar}{code}")
    public List<Menu> findMenus4Role(String code) {
        return menuMapper.selectMenus4Role(code);
    }

    @Override
    @Log
    @Cache(key = "menu:all")
    public List<Menu> findAllMenus() {
        Example example = new Example(Menu.class);
        example.setOrderByClause("sort asc");

        List<Menu> menus = myMapper.selectByExample(example);
        List<Menu> wrapList = new ArrayList();

        return recursionTreeList(menus, wrapList, "", 0L);
    }

    @Override
    @Log
    public Menu findMenuByCode(String code) {
        Menu menu = new Menu();
        menu.setCode(code);

        return myMapper.selectOne(menu);
    }

    @Override
    @Log
    @CacheDel(key = "menu:all")
    @Monitor(type = MonitorType.INSERT, description = "保存菜单${symbol_dollar}{menu.name}")
    public void saveMenu(Menu menu) {
        myMapper.insertSelective(menu);
    }

    @Override
    @Log
    @Cache(key = "menu:id:${symbol_dollar}{id}")
    public Menu findMenuById(Long id) {
        return myMapper.selectByPrimaryKey(id);
    }

    @Override
    @Log
    @Monitor(type = MonitorType.UPDATE, description = "更新菜单${symbol_dollar}{menu.code}")
    @CacheDel(key = {"menu:id:${symbol_dollar}{menu.id}", "menu:all", "menu:username*", "menu:role*"})
    public void updateMenu(Menu menu) {
        myMapper.updateByPrimaryKeySelective(menu);
    }

    @Override
    @Log
    @Monitor(type = MonitorType.DELETE, description = "删除菜单id=${symbol_dollar}{menu.id}")
    @CacheDel(key = {"menu:id:${symbol_dollar}{menu.id}", "menu:all", "menu:username*", "menu:role*"})
    public void deleteMenu(Menu menu) {
        myMapper.deleteByPrimaryKey(menu);
    }

    /**
     * 递归找出 parentCode 下边的所有子节点
     *
     * @param from
     * @param toList
     * @param pcode
     * @param pid
     * @return
     */
    private List<Menu> recursionList(List<Menu> from, List<Menu> toList, String pcode, Long pid) {

        if (CollectionUtils.isEmpty(from)) {
            return null;
        }

        for (int i = 0; i < from.size(); i++) {
            Menu menu = from.get(i);
            if (pcode.equals(menu.getPcode())) {
                List<Menu> leaf = new ArrayList();
                menu.setLeaf(leaf);
                menu.setPid(pid);
                toList.add(menu);
                recursionList(from, leaf, menu.getCode(), menu.getId());
            }
        }
        return toList;
    }

    /**
     * 递归找出 parentCode 下边的所有子节点
     *
     * @param from
     * @param toList
     * @param pcode
     * @param pid
     * @return
     */
    private List<Menu> recursionTreeList(List<Menu> from, List<Menu> toList, String pcode, Long pid) {

        if (CollectionUtils.isEmpty(from)) {
            return null;
        }

        for (int i = 0; i < from.size(); i++) {
            Menu menu = from.get(i);
            if (pcode.equals(menu.getPcode())) {
                menu.setPid(pid);
                toList.add(menu);
                recursionTreeList(from, toList, menu.getCode(), menu.getId());
            }
        }
        return toList;
    }

}
