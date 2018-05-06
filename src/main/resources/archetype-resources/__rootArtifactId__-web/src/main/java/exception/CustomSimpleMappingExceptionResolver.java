#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author kangyonggan
 * @since 5/4/18
 */
@Log4j2
public class CustomSimpleMappingExceptionResolver extends SimpleMappingExceptionResolver {

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.error("控制器异常", ex);
        String viewName = determineViewName(ex, request);
        if (viewName != null) {
            // ftl格式返回
            boolean isCommonReq = !(request.getHeader("accept").contains("application/json") || (request.getHeader("X-Requested-With") != null && request
                    .getHeader("X-Requested-With").contains("XMLHttpRequest")));
            if (isCommonReq) {
                // 如果不是异步请求
                Integer statusCode = determineStatusCode(request, viewName);
                if (statusCode != null) {
                    applyStatusCodeIfPossible(request, response, statusCode);
                }
                return getModelAndView(viewName, ex, request);
            } else {// 返回内嵌界面
                return getModelAndView("500", ex, request);
            }
        } else {
            return null;
        }
    }
}
