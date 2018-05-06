#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.impl;

import com.github.pagehelper.PageHelper;
import ${groupId}.app.constants.MonitorType;
import ${package}.constants.YesNo;
import ${groupId}.app.bean.Params;
import ${groupId}.app.bean.Query;
import ${package}.model.Dictionary;
import ${package}.service.DictionaryService;
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
public class DictionaryServiceImpl extends BaseService<Dictionary> implements DictionaryService {

    @Override
    public List<Dictionary> searchDictionaries(Params params) {
        Example example = new Example(Dictionary.class);
        Query query = params.getQuery();

        Example.Criteria criteria = example.createCriteria();
        String type = query.getString("type");
        if (StringUtils.isNotEmpty(type)) {
            criteria.andEqualTo("type", type);
        }
        String code = query.getString("code");
        if (StringUtils.isNotEmpty(code)) {
            criteria.andEqualTo("code", code);
        }
        String value = query.getString("value");
        if (StringUtils.isNotEmpty(value)) {
            criteria.andEqualTo("value", value);
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
    @Monitor(type = MonitorType.INSERT, description = "保存字典:${symbol_dollar}{dictionary.code}")
    public void saveDictionary(Dictionary dictionary) {
        myMapper.insertSelective(dictionary);
    }

    @Override
    @Log
    public Dictionary findDictionaryById(Long id) {
        return myMapper.selectByPrimaryKey(id);
    }

    @Override
    @Log
    @Monitor(type = MonitorType.UPDATE, description = "更新字典:${symbol_dollar}{dictionary.id}")
    public void updateDictionary(Dictionary dictionary) {
        myMapper.updateByPrimaryKeySelective(dictionary);
    }

    @Override
    @Log
    @Monitor(type = MonitorType.DELETE, description = "删除字典:${symbol_dollar}{id}")
    public void deleteDictionaryById(Long id) {
        myMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Log
    public boolean existsDictionary(String type, String code) {
        Dictionary dictionary = new Dictionary();
        dictionary.setType(type);
        dictionary.setCode(code);
        return super.exists(dictionary);
    }

    @Override
    @Log
    public List<Dictionary> findDictionariesByType(String type) {
        Example example = new Example(Dictionary.class);
        example.createCriteria().andEqualTo("isDeleted", YesNo.NO.getCode()).andEqualTo("type", type);

        example.setOrderByClause("sort asc");
        return myMapper.selectByExample(example);
    }

    @Override
    @Log
    @Monitor(type = MonitorType.DELETE, description = "批量删除字典:${symbol_dollar}{ids}")
    public void deleteDictionaries(String ids) {
        Dictionary dictionary = new Dictionary();
        dictionary.setIsDeleted(YesNo.YES.getCode());
        String[] arr = ids.split(",");

        Example example = new Example(Dictionary.class);
        example.createCriteria().andIn("id", Arrays.asList(arr));

        myMapper.updateByExampleSelective(dictionary, example);

    }
}
