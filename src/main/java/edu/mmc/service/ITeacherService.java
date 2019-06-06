package edu.mmc.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.mmc.entity.Teacher;
import edu.mmc.entity.vo.TeacherVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Huang
 * @since 2019-04-12
 */
public interface ITeacherService extends IService<Teacher> {   //根据name模糊查询
     List<TeacherVo> getTup(String name);

     List<TeacherVo> atlist(Page page, String faculty, String name);

     Boolean updByoid(TeacherVo tec,Long oid);
}
