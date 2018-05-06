#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import ${groupId}.app.bean.Params;
import ${package}.model.Monitor;
import ${groupId}.extra.core.model.MonitorInfo;

import java.util.List;

/**
 * @author kangyonggan
 * @since 5/4/18
 */
public interface MonitorService {

    /**
     * 保存监控信息
     *
     * @param monitorInfo 监控信息
     */
    void saveMonitor(MonitorInfo monitorInfo);

    /**
     * 搜索操作日志
     *
     * @param params 参数
     * @return 返回符合条件的操作日志
     */
    List<Monitor> searchMonitors(Params params);

}
