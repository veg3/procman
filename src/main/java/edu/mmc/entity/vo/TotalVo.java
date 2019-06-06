package edu.mmc.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)//无视ＮＵＬＬ
public class TotalVo {
    private Integer num;
    private String faculty;
}
