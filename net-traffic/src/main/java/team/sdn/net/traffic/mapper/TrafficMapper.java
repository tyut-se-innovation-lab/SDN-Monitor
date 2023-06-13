package team.sdn.net.traffic.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import team.sdn.net.traffic.entity.Traffic;

import java.util.List;

/**
 * @author TokisakiKurumi
 * @className TrafficMapper
 * @date 2023/6/1
 * @description 网络流量Mapper类
 **/
@Mapper
public interface TrafficMapper {
    /**
     * 插入流量数据
     * @param rate
     */
    void insertTraffic(@Param("rate") Double rate);

    /**
     * 获取流量数据
     * @param nowTime 当前时间
     * @param pastTime 两天前时间
     * @return 流量集合
     */
    List<Traffic> getTraffic(@Param("nowTime") String nowTime,@Param("pastTime") String pastTime);

    /**
     * 删除过时数据
     * @param pastTime 过去时间
     */
    void deleteTraffic(@Param("pastTime") String pastTime);
}
