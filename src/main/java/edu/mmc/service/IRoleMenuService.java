package edu.mmc.service;

import edu.mmc.entity.RoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.mmc.entity.vo.RoleMenuVo;

import java.util.List;

/**
 * <p>
 * 角色菜单关系表 角色菜单关系表 服务类
 * </p>
 *
 * @author Huang
 * @since 2019-04-11
 */
public interface IRoleMenuService extends IService<RoleMenu> {
    List<RoleMenuVo> menuByRoleId(Integer roleId);
}
