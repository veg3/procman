package edu.mmc.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import edu.mmc.entity.StuProj;
import edu.mmc.entity.TuProj;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import java.util.List;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)//无视ＮＵＬＬ
public class ItemVo{
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

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
     */
    private String applyDate;
    /**
     * 实施时间
     */
    private String executeDate;

    /**
     * 级别(0: 校级 ,1: 省级 ,2: 国家级 )
     */
    private Integer level;

    private Long stuId;

    private String stuName;

    private Long tutorId;

    /**
     * 院系
     */
    private String faculty;

    private String major;
    /**
     * 项目简介
     */
    private String intro;

    List<StuProjVo> stmb;

    List<TuProjVo> tumb;


}
