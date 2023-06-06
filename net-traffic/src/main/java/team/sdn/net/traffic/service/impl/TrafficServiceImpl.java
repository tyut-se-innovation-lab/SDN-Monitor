package team.sdn.net.traffic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.sdn.net.traffic.mapper.TrafficMapper;
import team.sdn.net.traffic.service.TrafficService;

/**
 * @author TokisakiKurumi
 * @className TrafficServiceImpl
 * @date 2023/6/2
 * @description
 **/
@Service
public class TrafficServiceImpl implements TrafficService {

    @Autowired
    private TrafficMapper mapper;

    @Override
    public String getAveByteRate() {
        return null;
    }

    @Override
    public String getInstantByteRate() {
        return null;
    }

    @Override
    public String getAvePackRate() {
        return null;
    }

    @Override
    public String getInstantPackRate() {
        return null;
    }
}
