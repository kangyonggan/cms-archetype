#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.impl;

import com.github.pagehelper.PageHelper;
import ${groupId}.app.bean.Params;
import ${groupId}.app.bean.Query;
import ${groupId}.app.constants.MonitorType;
import ${package}.constants.YesNo;
import ${groupId}.app.util.StringUtil;
import ${package}.mapper.RoleMapper;
import ${package}.model.Role;
import ${package}.service.RoleService;
import ${groupId}.extra.core.annotation.Cache;
import ${groupId}.extra.core.annotation.CacheDel;
import ${groupId}.extra.core.annotation.Log;
import ${groupId}.extra.core.annotation.Monitor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author kangyonggan
 * @since 5/4/18
 */
@Service
public class RoleServiceImpl extends BaseService<Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    @Log
    @Cache(key = "role:username:${symbol_dollar}{username}")
    public List<Role> findRolesByUsername(String username) {
        return roleMapper.selectRolesByUsername(username);
    }

    @Override
    @Log
    public boolean existsRoleCode(String code) {
        Role role = new Role();
        role.setCode(code);

        return super.exists(role);
    }

    @Override
    @Log
    @Cache(key = "role:all")
    public List<Role> findAllRoles() {
        Role role = new Role();
        role.setIsDeleted(YesNo.NO.getCode());

        return myMapper.select(role);
    }

    @Override
    public List<Role> searchRoles(Params params) {
        Example example = new Example(Role.class);
        Query query = params.getQuery();

        Example.Criteria criteria = example.createCriteria();
        String code = query.getString("code");
        if (StringUtils.isNotEmpty(code)) {
            criteria.andLike("code", StringUtil.toLike(code));
        }
        String name = query.getString("name");
        if (StringUtils.isNotEmpty(name)) {
            criteria.andLike("name", StringUtil.toLike(name));
        }

        Date beginDate = query.getDate("beginDate");
        if (beginDate != null) {
            criteria.andGreaterThanOrEqualTo("createdTime", beginDate);
        }
        Date endDate = query.getDate("endDate");
        if (endDate != null) {
            criteria.andLessThanOrEqualTo("createdTime", endDate);
        }

        String sort = params.getSort();
        String order = params.getOrder();
        if (!StringUtil.hasEmpty(sort, order)) {
            example.setOrderByClause(sort + " " + order.toUpperCase());
        }

        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        return myMapper.selectByExample(example);
    }

    @Override
    @Log
    @Monitor(type = MonitorType.INSERT, description = "保存角色${symbol_dollar}{role.code}")
    public void saveRole(Role role) {
        myMapper.insertSelective(role);
    }

    @Override
    @Log
    @Monitor(type = MonitorType.UPDATE, description = "更新角色${symbol_dollar}{role.code}")
    @CacheDel(key = {"role:code:${symbol_dollar}{role.code}", "role:all", "role:username*", "menu:username*"})
    public void updateRole(Role role) {
        Example example = new Example(Role.class);
        example.createCriteria().andEqualTo("code", role.getCode());

        myMapper.updateByExampleSelective(role, example);
    }

    @Override
    @Log
    @Monitor(type = MonitorType.UPDATE, description = "更新角色菜单${symbol_dollar}{code}, ${symbol_dollar}{menuCodes}")
    @CacheDel(key = {"menu:role:${symbol_dollar}{code}", "menu:username*"})
    public void updateRoleMenus(String code, String menuCodes) {
        deleteRoleMenus(code);

        if (StringUtils.isNotEmpty(menuCodes)) {
            roleMapper.insertRoleMenus(code, Arrays.asList(menuCodes.split(",")));
        }
    }

    @Override
    @Log
    @Cache(key = "role:code:${symbol_dollar}{code}")
    public Role findRoleByCode(String code) {
        Role role = new Role();
        role.setCode(code);

        return myMapper.selectOne(role);
    }

    @Override
    @Log
    @Monitor(type = MonitorType.DELETE, description = "删除角色${symbol_dollar}{code}")
    @CacheDel(key = {"role:code:${symbol_dollar}{code}", "role:all", "role:username*", "menu:username*"})
    public void deleteRoleByCode(String code) {
        Role role = new Role();
        role.setCode(code);

        myMapper.delete(role);
    }

    /**
     * 删除角色菜单
     *
     * @param code
     */
    private void deleteRoleMenus(String code) {
        roleMapper.deleteRoleMenus(code);
    }
}
