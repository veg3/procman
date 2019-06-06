package edu.mmc.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.mmc.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Huang
 * @since 2019-04-12
 */
public interface IStudentService extends IService<Student> {
    List aslist(Page page,String faculty,String major,String name);

    Boolean updByoid(Student stu,Long oid);
}
