#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller.web;

import ${groupId}.app.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author kangyonggan
 * @since 5/4/18
 */
@Controller
@RequestMapping("/")
public class IndexController extends BaseController {

    /**
     * 首页
     *
     * @return 返回首页界面
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return getPathIndex();
    }

}
