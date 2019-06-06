package edu.mmc.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)//无视ＮＵＬＬ
@AllArgsConstructor
@NoArgsConstructor
public class StuProjVo {
    private static final long serialVersionUID = 1L;

    /**
     * 学号
     */
    private Long id;

    /**
     * 学生姓名
     */
    private String name;

    /**
     * 院系
     */
    private String faculty;

    /**
     * 专业
     */
    private String major;

    /**
     * 年级
     */
    private String grade;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;
}
