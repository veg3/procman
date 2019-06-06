package edu.mmc.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sun.tracing.dtrace.ProviderAttributes;
import edu.mmc.entity.vo.AifVo;
import edu.mmc.entity.vo.AuditVo;
import edu.mmc.entity.vo.QueryVo;
import edu.mmc.entity.vo.SystemUserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface  DloMapper {
     String attach(@Param("tid")Integer tid,@Param("status")Integer status);
     String docu(@Param("tid")Integer tid,@Param("status")Integer status);
     List<AifVo> aifList(IPage ipage, @Param("status")Integer status, @Param("user")SystemUserVo user, @Param("qvo") QueryVo qvo);
     boolean backunp(@Param("status")Integer status,@Param("avo")AuditVo avo,
                            @Param("tid")Integer tid);
}
