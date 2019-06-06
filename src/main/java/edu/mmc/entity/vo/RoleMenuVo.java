package edu.mmc.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 菜单表 菜单表
 * </p>
 *
 * @author CDHong
 * @since 2018-11-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)//无视ＮＵＬＬ
public class RoleMenuVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色编号 角色编号
     */
    private Integer roleId;

    /**
     * 菜单编号 菜单编号
     */
    private Integer menuId;

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
     * 地址 上级编号
     */
    private String href;
    /**
     * 是否展开 是否展开
     */
    private BigDecimal isSpread;

    private List<RoleMenuVo> children;


}
