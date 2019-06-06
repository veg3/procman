package edu.mmc.service;

import edu.mmc.entity.SystemUser;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.mmc.entity.vo.RoleMenuVo;
import edu.mmc.entity.vo.SystemUserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Huang
 * @since 2019-04-11
 */
public interface ISystemUserService{
    SystemUserVo getUserVoByUid(String user_id);

    List<RoleMenuVo> getMenus(Integer roleId);


    Integer existUser(String  uid,String pwd);

    SystemUser getUser(String uid);

    Boolean updatepwd(String uid,String pwd);


}
