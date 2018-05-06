#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.handle;

import ${groupId}.app.util.SpringUtils;
import ${package}.service.MonitorService;
import ${groupId}.extra.core.annotation.Handle;
import ${groupId}.extra.core.handle.MonitorHandle;
import ${groupId}.extra.core.model.MonitorInfo;
import lombok.extern.log4j.Log4j2;

/**
 * 方法监控
 *
 * @author kangyonggan
 * @since 3/30/18
 */
@Handle(type = Handle.Type.MONITOR)
@Log4j2
public class DbMonitorHandle implements MonitorHandle {

    /**
     * 监控服务
     */
    private MonitorService monitorService;

    /**
     * 监控
     *
     * @param monitorInfo 监控信息
     * @return 返回方法的返回值
     */
    @Override
    public Object handle(MonitorInfo monitorInfo) {
        try {
            getMonitorService().saveMonitor(monitorInfo);
        } catch (Exception e) {
            log.error("保存监控信息异常", e);
        }
        return monitorInfo.getReturnValue();
    }

    /**
     * 获取监控服务
     *
     * @return 返回监控服务
     */
    private MonitorService getMonitorService() {
        if (monitorService == null) {
            monitorService = SpringUtils.getBean(MonitorService.class);
        }

        return monitorService;
    }
}

