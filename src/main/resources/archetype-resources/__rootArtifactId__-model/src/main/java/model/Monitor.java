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
@Table(name = "tb_monitor")
@Data
public class Monitor implements Serializable {
    /**
     * 主键, 自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 应用名称
     */
    private String app;

    /**
     * 监控类型
     */
    private String type;

    /**
     * 监控描述
     */
    private String description;

    /**
     * 开始时间
     */
    @Column(name = "begin_time")
    private Date beginTime;

    /**
     * 结束时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 是否有返回值:{0:没有返回值, 1:有返回值}
     */
    @Column(name = "has_return_value")
    private Byte hasReturnValue;

    /**
     * 用户名
     */
    private String username;

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

    /**
     * 返回值
     */
    @Column(name = "return_value")
    private String returnValue;

    /**
     * 参数
     */
    private String args;

    private static final long serialVersionUID = 1L;
}