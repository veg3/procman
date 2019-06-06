package edu.mmc.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Huang
 * @since 2019-04-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 教工号
     */
    private Long id;

    /**
     * 教师姓名
     */
    private String name;

    /**
     * 院系
     */
    private String faculty;

    /**
     * 教师职称
     */
    private String jobTitle;

    /**
     * 研究方向
     */
    private String resDirect;

    /**
     * 身份证号
     */
    private String idCard;

    private String password;

    private Integer roleId;
}
