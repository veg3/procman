package edu.mmc.service.impl;

import edu.mmc.entity.Major;
import edu.mmc.mapper.MajorMapper;
import edu.mmc.service.IMajorService;
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
public class MajorServiceImpl extends ServiceImpl<MajorMapper, Major> implements IMajorService {
    @Autowired
    private MajorMapper mje;
    @Override
    public List<String> getmjNames(String faN) {
        return mje.mjNames(faN);
    }
}
