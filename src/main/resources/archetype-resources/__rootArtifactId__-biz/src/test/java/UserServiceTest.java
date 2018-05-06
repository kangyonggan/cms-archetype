#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import ${package}.model.User;
import ${package}.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author kangyonggan
 * @date 4/11/17
 */
@Log4j2
public class UserServiceTest extends AbstractServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testFindUserByUsername() {
        User user = userService.findUserByUsername("admin");
        log.info(user);
    }

}
