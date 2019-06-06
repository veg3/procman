package edu.mmc.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import edu.mmc.entity.StuProj;
import edu.mmc.entity.TuProj;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)//无视ＮＵＬＬ
public class AlistVo {

        private static final long serialVersionUID = 1L;

        private Integer id;

        private Integer statId;

        private Integer status;

        private String name;

        private String tpName;

        private String stuName;

        private String tuName;

        private String statName;

        @JsonFormat(pattern = "yyyy-MM-dd")
        @DateTimeFormat(pattern="yyyy-MM-dd")
        private Date applyDate;

        private String faculty;

        private String major;

}

