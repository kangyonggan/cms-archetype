#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.impl;

import com.github.pagehelper.PageHelper;
import ${groupId}.app.constants.MonitorType;
import ${package}.constants.YesNo;
import ${groupId}.app.bean.Params;
import ${groupId}.app.bean.Query;
import ${package}.model.DictionaryType;
import ${package}.service.DictionaryTypeService;
import ${groupId}.app.util.StringUtil;
import ${groupId}.extra.core.annotation.Log;
import ${groupId}.extra.core.annotation.Monitor;
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
public class DictionaryServiceTypeImpl extends BaseService<DictionaryType> implements DictionaryTypeService {
    @Override
    public List<DictionaryType> searchDictionaryTypes(Params params) {
        Example example = new Example(DictionaryType.class);
        Query query = params.getQuery();

        Example.Criteria criteria = example.createCriteria();
        String type = query.getString("type");
        if (StringUtils.isNotEmpty(type)) {
            criteria.andLike("type", StringUtil.toLike(type));
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
    @Monitor(type = MonitorType.INSERT, description = "新增字典类型:${symbol_dollar}{dictionaryType.type}")
    public void saveDictionaryType(DictionaryType dictionaryType) {
        myMapper.insertSelective(dictionaryType);
    }

    @Override
    @Log
    @Monitor(type = MonitorType.UPDATE, description = "更新字典类型:${symbol_dollar}{dictionaryType.type}")
    public void updateDictionaryType(DictionaryType dictionaryType) {
        myMapper.updateByPrimaryKeySelective(dictionaryType);
    }

    @Override
    @Log
    public DictionaryType findDictionaryTypeById(Long id) {
        return myMapper.selectByPrimaryKey(id);
    }

    @Override
    @Log
    @Monitor(type = MonitorType.DELETE, description = "删除字典类型:${symbol_dollar}{id}")
    public void deleteDictionaryTypeById(Long id) {
        myMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Log
    public boolean existsDictionaryType(String type) {
        DictionaryType dictionaryType = new DictionaryType();
        dictionaryType.setType(type);
        return super.exists(dictionaryType);
    }

    @Override
    @Log
    public List<DictionaryType> findAllDictionaryTypes() {
        DictionaryType dictionaryType = new DictionaryType();
        dictionaryType.setIsDeleted(YesNo.NO.getCode());

        return myMapper.select(dictionaryType);
    }
}
