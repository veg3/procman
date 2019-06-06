package edu.mmc.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import edu.mmc.entity.SystemUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.mmc.entity.vo.SystemUserVo;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Huang
 * @since 2019-04-11
 */
public interface SystemUserMapper extends BaseMapper<SystemUser> {
    SystemUserVo getUserVoByUid(@Param("uid") String uid);

    Integer existUser(@Param("uid")String uid,@Param("pwd")String pwd);

    SystemUser getUser(@Param("uid")String uid);

    Boolean updatepwd(@Param("uid")String uid,@Param("pwd")String pwd);
}
