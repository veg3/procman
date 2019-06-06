package edu.mmc.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.mmc.entity.Item;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.mmc.entity.vo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.awt.print.PrinterAbortException;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Huang
 * @since 2019-04-17
 */
public interface ItemMapper extends BaseMapper<Item> {
     Boolean updvo(@Param("vo")ItemVo vo);

     Boolean insVo(@Param("vo")ItemVo vo);

     List<AlistVo> itemsPage(IPage page, @Param("rid") Integer rid, @Param("fval") String fval, @Param("qvo")QueryVo qvo);

     Boolean updStat(@Param("id")Integer id,@Param("val")Integer val);

     List<TotalVo> gettda(@Param("fastr") List<String> str, @Param("type") Integer type,
                                    @Param("qvo")QueryVo qvo);

     List<TotalVo> getsda(@Param("fastr") List<String> str, @Param("sid") Integer sid,
                                @Param("qvo")QueryVo qvo);

     FnsVo aifNs(@Param("id") Integer id);

     ItemVo getitemVo(@Param("id") Integer id);
}
