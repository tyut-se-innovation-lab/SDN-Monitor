package com.net.quartz.task;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team.sdn.net.traffic.mapper.TrafficMapper;

/**
 * @author TokisakiKurumi
 * @className TrafficTask
 * @date 2023/6/13
 * @description 定期清理过时数据
 **/
@Component("TrafficTask")
public class TrafficTask {

    @Autowired
    TrafficMapper mapper;

    /**
     * 清理流量表
     */
    public void cleanTable() {
        LocalDateTime tempTime = LocalDateTime.now().withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59);
        LocalDateTime pastTime = tempTime.minusDays(2);
        mapper.deleteTraffic(pastTime.toString("yyyy-MM-dd HH:mm:ss"));
    }
}
