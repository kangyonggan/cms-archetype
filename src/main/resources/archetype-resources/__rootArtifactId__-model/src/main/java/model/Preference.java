#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author kangyonggan
 * @since 5/4/18
 */
@Table(name = "tb_preference")
@Data
public class Preference implements Serializable {
    /**
     * 主键, 自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 偏好类型
     */
    private String type;

    /**
     * 偏好名称
     */
    private String name;

    /**
     * 偏好的值
     */
    private String value;

    /**
     * 逻辑删除:{0:未删除, 1:已删除}
     */
    @Column(name = "is_deleted")
    private Byte isDeleted;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 更新时间
     */
    @Column(name = "updated_time")
    private Date updatedTime;

    private static final long serialVersionUID = 1L;
}