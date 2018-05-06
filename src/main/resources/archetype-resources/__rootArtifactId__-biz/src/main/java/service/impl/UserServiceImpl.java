#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.impl;

import com.github.pagehelper.PageHelper;
import ${groupId}.app.bean.Params;
import ${groupId}.app.bean.Query;
import ${groupId}.app.constants.AppConstants;
import ${groupId}.app.constants.MonitorType;
import ${package}.mapper.RoleMapper;
import ${package}.mapper.UserMapper;
import ${package}.model.User;
import ${package}.service.UserService;
import ${groupId}.extra.core.annotation.Cache;
import ${groupId}.extra.core.annotation.CacheDel;
import ${groupId}.extra.core.annotation.Log;
import ${groupId}.extra.core.annotation.Monitor;
import ${groupId}.app.util.Digests;
import ${groupId}.app.util.Encodes;
import ${groupId}.app.util.StringUtil;
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
public class UserServiceImpl extends BaseService<User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    @Cache(key = "user:username:${symbol_dollar}{username}")
    public User findUserByUsername(String username) {
        User user = new User();
        user.setUsername(username);
        return myMapper.selectOne(user);
    }

    @Override
    public List<User> searchUsers(Params params) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        Query query = params.getQuery();

        String username = query.getString("username");
        if (StringUtils.isNotEmpty(username)) {
            criteria.andLike("username", StringUtil.toLike(username));
        }

        String realname = query.getString("realname");
        if (StringUtils.isNotEmpty(realname)) {
            criteria.andLike("realname", StringUtil.toLike(realname));
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
    @Monitor(type = MonitorType.UPDATE, description = "更新用户${symbol_dollar}{user.username}")
    @CacheDel(key = {"user:username:${symbol_dollar}{user.username}", "role:username:${symbol_dollar}{user.username}", "menu:username:${symbol_dollar}{user.username}"})
    public void updateUserByUsername(User user) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username", user.getUsername());

        if (StringUtils.isNotEmpty(user.getPassword())) {
            entryptPassword(user);
        }

        myMapper.updateByExampleSelective(user, example);
    }

    @Override
    @Log
    public boolean existsUsername(String username) {
        User user = new User();
        user.setUsername(username);
        return super.exists(user);
    }

    @Override
    @Log
    @Monitor(type = MonitorType.INSERT, description = "保存用户${symbol_dollar}{user.username}")
    public void saveUserWithDefaultRole(User user) {
        entryptPassword(user);

        myMapper.insertSelective(user);

        saveUserRoles(user.getUsername(), "ROLE_USER");
    }

    @Override
    @Log
    @CacheDel(key = "user:username:${symbol_dollar}{user.username}")
    @Monitor(type = MonitorType.UPDATE, description = "更新密码${symbol_dollar}{user.username}")
    public void updateUserPassword(User user) {
        User tUser = new User();
        tUser.setUsername(user.getUsername());
        tUser.setPassword(user.getPassword());
        tUser.setSalt(user.getSalt());

        entryptPassword(tUser);

        updateUserByUsername(tUser);
    }

    @Override
    @Log
    @Monitor(type = MonitorType.DELETE, description = "删除用户${symbol_dollar}{username}")
    @CacheDel(key = {"role:username:${symbol_dollar}{username}", "menu:username:${symbol_dollar}{username}"})
    public void deleteUserByUsername(String username) {
        User user = new User();
        user.setUsername(username);
        myMapper.delete(user);
    }

    @Override
    @Log
    @Monitor(type = MonitorType.UPDATE, description = "更新用户角色${symbol_dollar}{username}, ${symbol_dollar}{roleCodes}")
    @CacheDel(key = {"role:username:${symbol_dollar}{username}", "menu:username:${symbol_dollar}{username}"})
    public void updateUserRoles(String username, String roleCodes) {
        roleMapper.deleteAllRolesByUsername(username);

        if (StringUtils.isNotEmpty(roleCodes)) {
            saveUserRoles(username, roleCodes);
        }
    }

    /**
     * 批量保存用户角色
     *
     * @param username
     * @param roleCodes
     */
    private void saveUserRoles(String username, String roleCodes) {
        userMapper.insertUserRoles(username, Arrays.asList(roleCodes.split(",")));
    }

    /**
     * 设定安全的密码，生成随机的salt并经过N次 sha-1 hash
     *
     * @param user
     */
    private void entryptPassword(User user) {
        byte[] salt = Digests.generateSalt(AppConstants.SALT_SIZE);
        user.setSalt(Encodes.encodeHex(salt));

        byte[] hashPassword = Digests.sha1(user.getPassword().getBytes(), salt, AppConstants.HASH_INTERATIONS);
        user.setPassword(Encodes.encodeHex(hashPassword));
    }
}
