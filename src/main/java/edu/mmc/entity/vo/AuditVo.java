package edu.mmc.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)//无视ＮＵＬＬ
public class AuditVo {
    private String tuComm;
    private String faComm;
    private Integer faLev;
    private String  leComm;
    private Integer faRes;
    private Integer leRes;
}
