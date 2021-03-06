#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.mapper;

import ${package}.model.DictionaryType;
import ${groupId}.app.mapper.MyMapper;
import org.springframework.stereotype.Repository;

/**
 * @author kangyonggan
 * @since 5/4/18
 */
@Repository
public interface DictionaryTypeMapper extends MyMapper<DictionaryType> {
}