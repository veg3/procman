package edu.mmc.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)//无视ＮＵＬＬ
public class TeacherVo {

        /**
         * 教工号
         */
        private Long id;

        /**
         * 教师姓名
         */
        private String name;

        /**
         * 院系
         */
        private String faculty;

        /**
         * 教师职称
         */
        private String jobTitle;

        /**
         * 研究方向
         */
        private String resDirect;

        private Integer roleid;
}
