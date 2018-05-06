#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.mapper;

import ${groupId}.app.mapper.MyMapper;
import ${package}.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author kangyonggan
 * @since 5/4/18
 */
@Repository
public interface UserMapper extends MyMapper<User> {

    /**
     * 保存用户角色
     *
     * @param username  用户名
     * @param roleCodes 角色代码
     */
    void insertUserRoles(@Param("username") String username, @Param("roleCodes") List<String> roleCodes);

}