package edu.mmc.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)//无视ＮＵＬＬ
public class QueryVo {
    private String name;

    private String faculty;

    private String executeDate;

    private String sufDate;

    private String posDate;
}
