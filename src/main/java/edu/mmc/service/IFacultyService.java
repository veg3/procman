package edu.mmc.service;

import edu.mmc.entity.Faculty;
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
public interface IFacultyService extends IService<Faculty> {
    public List<String> getFaNames();
}
