#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.impl;

import com.github.pagehelper.PageHelper;
import ${groupId}.app.bean.Params;
import ${groupId}.app.bean.Query;
import ${package}.model.LoginLog;
import ${package}.service.LoginLogService;
import ${groupId}.app.util.StringUtil;
import ${groupId}.extra.core.annotation.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author kangyonggan
 * @since 5/4/18
 */
@Service
public class LoginLogServiceImpl extends BaseService<LoginLog> implements LoginLogService {

    @Override
    @Log
    public void saveLoginLog(String username, String ip) {
        LoginLog loginLog = new LoginLog();
        loginLog.setUsername(username);
        loginLog.setIp(ip);

        myMapper.insertSelective(loginLog);
    }

    @Override
    public List<LoginLog> searchLoginLogs(Params params) {
        Example example = new Example(LoginLog.class);
        Example.Criteria criteria = example.createCriteria();
        Query query = params.getQuery();

        String username = query.getString("username");
        if (StringUtils.isNotEmpty(username)) {
            criteria.andLike("username", StringUtil.toLike(username));
        }

        String ip = query.getString("ip");
        if (StringUtils.isNotEmpty(ip)) {
            criteria.andLike("ip", StringUtil.toLike(ip));
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
}
