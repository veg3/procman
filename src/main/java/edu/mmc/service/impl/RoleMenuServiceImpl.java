package edu.mmc.service.impl;

import edu.mmc.entity.RoleMenu;
import edu.mmc.entity.vo.RoleMenuVo;
import edu.mmc.mapper.RoleMenuMapper;
import edu.mmc.service.IRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色菜单关系表 角色菜单关系表 服务实现类
 * </p>
 *
 * @author Huang
 * @since 2019-04-11
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {
    @Autowired
    RoleMenuMapper roleMenuMapper;

    @Override
    public List<RoleMenuVo> menuByRoleId(Integer roleId) {
        return roleMenuMapper.menuByRoleId(roleId);
    }

}
