package edu.mmc.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Huang
 * @since 2019-04-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class FinalReport implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 结题报告书ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 导师意见
     */
    private String tuComment;

    /**
     * 院系意见
     */
    private String faComment;

    /**
     * 教务处意见
     */
    private String leComment;

    /**
     * 结题报告
     */
    private String document;

    /**
     * 附件
     */
    private String attachment;

    /**
     * 院系结果
     */
    private Integer faResult;

    /**
     * 教务处结果
     */
    private Integer leResult;

    /**
     * 项目ID
     */
    private Integer itemId;


}
