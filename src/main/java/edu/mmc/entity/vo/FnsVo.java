package edu.mmc.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FnsVo {
    private String doc1;
    private String doc2;
    private String doc3;
    private String atta1;
    private String atta2;
    private String atta3;
}
