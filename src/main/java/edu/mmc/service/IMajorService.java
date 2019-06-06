package edu.mmc.service;

import edu.mmc.entity.Major;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Huang
 * @since 2019-04-24
 */
public interface IMajorService extends IService<Major> {
    public List<String> getmjNames(String faN);
}
