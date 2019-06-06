package edu.mmc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.mmc.entity.SystemUser;
import edu.mmc.entity.vo.RoleMenuVo;
import edu.mmc.entity.vo.SystemUserVo;
import edu.mmc.mapper.RoleMenuMapper;
import edu.mmc.mapper.SystemUserMapper;
import edu.mmc.service.ISystemUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Huang
 * @since 2019-04-11
 */
@Service
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser> implements ISystemUserService {
    @Autowired
    private SystemUserMapper sym;

    @Autowired
    private RoleMenuMapper rolm;


    @Override
    public SystemUserVo getUserVoByUid(String userid) {
        return sym.getUserVoByUid(userid);
    }
    public Integer existUser(String uid,String pwd){
        return sym.existUser(uid,pwd);
    }
    @Override
    public List<RoleMenuVo> getMenus(Integer roleId) {
        List<RoleMenuVo> results = new ArrayList<>();
        List<RoleMenuVo> list = rolm.menuByRoleId(roleId);
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getParentId() == 0) {
                    RoleMenuVo menu = new RoleMenuVo();
                    menu.setMeunName(list.get(i).getMeunName());
                    menu.setMenuIcon(list.get(i).getMenuIcon());
                    menu.setHref(list.get(i).getHref());
                    menu.setIsSpread(list.get(i).getIsSpread());
                    List<RoleMenuVo> list2 = new ArrayList<>();
                    for (int j = 0; j < list.size(); j++) {
                        if (list.get(j).getParentId() == list.get(i).getMenuId()) {
                            RoleMenuVo menu2 = new RoleMenuVo();
                            menu2.setMeunName(list.get(j).getMeunName());
                            menu2.setMenuIcon(list.get(j).getMenuIcon());
                            menu2.setHref(list.get(j).getHref());
                            menu2.setIsSpread(list.get(j).getIsSpread());
                            list2.add(menu2);
                        }
                    }
                    menu.setChildren(list2);
                    results.add(menu);
                }
            }
        }
        return results;
    }


    public SystemUser getUser(String uid){
        return sym.getUser(uid);
    }
    public  Boolean updatepwd(String uid,String pwd){
        return sym.updatepwd(uid,pwd);
    }
}
