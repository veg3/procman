package edu.mmc.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜单表 菜单表
 * </p>
 *
 * @author Huang
 * @since 2019-04-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号 编号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 菜单名称 菜单名称
     */
    private String meunName;

    /**
     * 菜单图标 菜单图表
     */
    private String menuIcon;

    /**
     * 上级编号 上级编号
     */
    private Integer parentId;

    /**
     * 地址
     */
    private String href;

    /**
     * 是否展开 是否展开
     */
    private BigDecimal isSpread;


}
