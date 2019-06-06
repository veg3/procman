package edu.mmc.service.impl;

import edu.mmc.entity.Menu;
import edu.mmc.mapper.MenuMapper;
import edu.mmc.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单表 菜单表 服务实现类
 * </p>
 *
 * @author Huang
 * @since 2019-04-11
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

}
