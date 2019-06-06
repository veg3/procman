package edu.mmc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Huang
 * @since 2019-04-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)//无视ＮＵＬＬ
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 项目ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 项目类型 (0: 创新训练 ,1: 创业训练 ,2: 创业实践 )
     */
    private Integer type;

    /**
     * 申报时间
     */
    private Date applyDate;

    /**
     * 实施时间
     */
    private String executeDate;

    /**
     * 级别(0: 校级 ,1: 省级 ,2: 国家级 )
     */
    private Integer level;

    /**
     * 院系
     */
    private String faculty;

    /**
     * 专业
     */
    private String major;

    /**
     * 项目简介
     */
    private String intro;

    /**
     * 项目负责人学号
     */
    private Long stuId;

    /**
     * 导师教工号
     */
    private Long tutorId;

    /**
     * 项目状态
     */
    private Integer status;

    /**
     * 二级状态
     */
    private Integer statId;


}
