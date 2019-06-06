package edu.mmc.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 系统用户 系统用户
 * </p>
 *
 * @author CDHong
 * @since 2018-11-22
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)//无视ＮＵＬＬ
public class SystemUserVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long userId;

    private String userName;

    private String pwd;

    private Integer roleId;

    private String roleName;

    private String faculty;

    private String newPwd;

}
