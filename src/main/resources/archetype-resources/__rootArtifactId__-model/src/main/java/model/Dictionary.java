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
@Table(name = "tb_dictionary")
@Data
public class Dictionary implements Serializable {
    /**
     * 主键, 自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 字典类型
     */
    private String type;

    /**
     * 字典代码
     */
    private String code;

    /**
     * 字典的值
     */
    private String value;

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序（从0开始）
     */
    private Integer sort;

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