package edu.mmc.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)//无视ＮＵＬＬ
public class FinalVo {
    private String faculty;
    private String name;
    private String stuName;
    private String phone;
    private String tuName;
    private String applyDate;
    private Integer type;
}
