package edu.mmc.service.impl;

import edu.mmc.entity.Role;
import edu.mmc.mapper.RoleMapper;
import edu.mmc.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 角色表 服务实现类
 * </p>
 *
 * @author Huang
 * @since 2019-04-11
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
