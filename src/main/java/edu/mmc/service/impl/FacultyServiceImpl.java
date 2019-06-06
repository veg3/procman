package edu.mmc.service.impl;

import edu.mmc.entity.Faculty;
import edu.mmc.mapper.FacultyMapper;
import edu.mmc.service.IFacultyService;
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
 * @since 2019-04-24
 */
@Service
public class FacultyServiceImpl extends ServiceImpl<FacultyMapper, Faculty> implements IFacultyService {
    @Autowired
    private FacultyMapper fma;
    @Override
    public List<String> getFaNames() {
        return fma.faNames();
    }
}
