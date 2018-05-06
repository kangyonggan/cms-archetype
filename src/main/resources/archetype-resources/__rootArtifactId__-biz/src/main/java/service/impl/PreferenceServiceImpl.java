#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.impl;

import com.github.pagehelper.PageHelper;
import ${groupId}.app.constants.MonitorType;
import ${groupId}.app.bean.Params;
import ${groupId}.app.bean.Query;
import ${package}.model.Preference;
import ${package}.service.PreferenceService;
import ${groupId}.app.util.StringUtil;
import ${groupId}.extra.core.annotation.Log;
import ${groupId}.extra.core.annotation.Monitor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;

/**
 * @author kangyonggan
 * @since 5/4/18
 */
@Service
public class PreferenceServiceImpl extends BaseService<Preference> implements PreferenceService {

    @Override
    @Log
    public Preference findPreferenceByTypeNameAndUsername(String type, String name, String username) {
        if (StringUtil.hasEmpty(type, name, username)) {
            return null;
        }
        Preference preference = new Preference();
        preference.setType(type);
        preference.setName(name);
        preference.setUsername(username);

        return myMapper.selectOne(preference);
    }

    @Override
    public List<Preference> searchPreferences(Params params) {
        Example example = new Example(Preference.class);
        Query query = params.getQuery();

        Example.Criteria criteria = example.createCriteria();
        String username = query.getString("username");
        if (StringUtils.isNotEmpty(username)) {
            criteria.andLike("username", StringUtil.toLike(username));
        }
        String type = query.getString("type");
        if (StringUtils.isNotEmpty(type)) {
            criteria.andEqualTo("type", type);
        }
        String name = query.getString("name");
        if (StringUtils.isNotEmpty(name)) {
            criteria.andLike("name", StringUtil.toLike(name));
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
    public Preference findPreferenceById(Long id) {
        return myMapper.selectByPrimaryKey(id);
    }

    @Override
    @Log
    @Monitor(type = MonitorType.UPDATE, description = "更新偏好type:${symbol_dollar}{preference.type},name:${symbol_dollar}{preference.name},value:${symbol_dollar}{preference.value}")
    public void updatePreference(Preference preference) {
        myMapper.updateByPrimaryKeySelective(preference);
    }

    @Override
    @Log
    @Monitor(type = MonitorType.DELETE, description = "删除偏好id:${symbol_dollar}{id}")
    public void deletePreferenceById(Long id) {
        myMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Log
    @Monitor(type = MonitorType.UPDATE, description = "更新或插入偏好type:${symbol_dollar}{type}，names=${symbol_dollar}{names},value:${symbol_dollar}{value}")
    public void updateOrSavePreferences(String username, String type, String names, String value) {
        String[] nameArr = names.split(",");
        for (String name : nameArr) {
            Preference preference = new Preference();
            preference.setValue(value);

            Example example = new Example(Preference.class);
            example.createCriteria().andEqualTo("type", type).andEqualTo("name", name);
            int count = myMapper.selectCountByExample(example);
            if (count > 0) {
                myMapper.updateByExampleSelective(preference, example);
            } else {
                preference.setType(type);
                preference.setName(name);
                preference.setUsername(username);
                myMapper.insertSelective(preference);
            }
        }
    }

    @Override
    @Log
    @Monitor(type = MonitorType.DELETE, description = "批量删除偏好ids:${symbol_dollar}{ids}")
    public void deletePreferenceByIds(String ids) {
        Example example = new Example(Preference.class);
        String[] arr = ids.split(",");
        example.createCriteria().andIn("id", Arrays.asList(arr));

        myMapper.deleteByExample(example);
    }
}
