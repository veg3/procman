package edu.mmc.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.mmc.entity.Student;
import edu.mmc.mapper.StudentMapper;
import edu.mmc.service.IStudentService;
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
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {
    @Autowired
    private StudentMapper stm;
    @Override
    public List aslist(Page page, String faculty, String major, String name) {
        return stm.aslist(page,faculty,major,name);
    }

    @Override
    public Boolean updByoid(Student stu, Long oid) {
        return stm.updByoid(stu,oid);
    }
}
