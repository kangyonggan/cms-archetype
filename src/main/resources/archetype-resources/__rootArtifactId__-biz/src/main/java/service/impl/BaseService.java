#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.impl;

import ${groupId}.app.mapper.MyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kangyonggan
 * @date 16/5/23
 */
@Service
public abstract class BaseService<T> {

    @Autowired
    protected MyMapper<T> myMapper;

    /**
     * 判断记录是否存在
     *
     * @param entity 实体
     * @return 如果实体存在返回true，否则返回false
     */
    public boolean exists(T entity) {
        return myMapper.selectCount(entity) > 0;
    }

}

