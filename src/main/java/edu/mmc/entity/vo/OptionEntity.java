package edu.mmc.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)//无视ＮＵＬＬ
public class OptionEntity {
    private String xAxis[];
    private List<Object> series;
    private String legend[];
    private String title;

}
