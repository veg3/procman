package edu.mmc.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TableDataVo {
    //layUI 数据表格的JSON
    private int code;
    private String msg;
    private long count;
    private Object data;//layui表格数据
    public static TableDataVo okData(long count, Object data){
        return new TableDataVo(0,"",count,data);
    }
    public static TableDataVo okMsg(String msg){
        return new TableDataVo(0,msg,0,null);
    }
    public static TableDataVo failMsg(String msg){
        return new TableDataVo(1,msg,0,null);
    }
    public static TableDataVo okData(Object data) {
        return new TableDataVo(0, null,0, data);
    }
}
