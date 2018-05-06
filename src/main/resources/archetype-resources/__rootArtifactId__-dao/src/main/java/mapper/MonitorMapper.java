#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.mapper;

import ${groupId}.app.mapper.MyMapper;
import ${package}.model.Monitor;
import org.springframework.stereotype.Repository;

/**
 * @author kangyonggan
 * @since 5/4/18
 */
@Repository
public interface MonitorMapper extends MyMapper<Monitor> {
}