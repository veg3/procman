package edu.mmc.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.mmc.entity.Teacher;
import edu.mmc.entity.vo.TeacherVo;
import edu.mmc.mapper.TeacherMapper;
import edu.mmc.service.ITeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Huang
 * @since 2019-04-12
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {
    @Autowired
    private TeacherMapper tcm;
    @Override
    public List<TeacherVo> getTup(String name) {
        return tcm.getTup(name);
    }

    public List<TeacherVo> atlist(Page page,String faculty, String name) {
        return tcm.atlist(page,faculty,name);
    }

    @Override
    public Boolean updByoid(TeacherVo tec,Long oid) {
        return tcm.updByoid(tec,oid);
    }

}
