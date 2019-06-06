package edu.mmc.mapper;

import edu.mmc.entity.RoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.mmc.entity.vo.RoleMenuVo;

import java.util.List;

/**
 * <p>
 * 角色菜单关系表 角色菜单关系表 Mapper 接口
 * </p>
 *
 * @author Huang
 * @since 2019-04-11
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {
    List<RoleMenuVo> menuByRoleId(Integer roleId);
}
