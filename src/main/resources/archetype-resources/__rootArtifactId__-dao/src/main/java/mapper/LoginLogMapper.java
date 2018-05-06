#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.mapper;

import ${package}.model.LoginLog;
import ${groupId}.app.mapper.MyMapper;
import org.springframework.stereotype.Repository;

/**
 * @author kangyonggan
 * @since 5/4/18
 */
@Repository
public interface LoginLogMapper extends MyMapper<LoginLog> {
}