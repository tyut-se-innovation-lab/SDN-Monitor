package team.sdn.net.traffic.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.joda.time.Duration;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import team.sdn.enums.SFlowStatistic;
import team.sdn.net.traffic.entity.Traffic;
import team.sdn.net.traffic.mapper.TrafficMapper;
import team.sdn.net.traffic.service.TrafficService;
import team.sdn.sdnutils.NetflowUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author TokisakiKurumi
 * @className TrafficServiceImpl
 * @date 2023/6/2
 * @description TrafficService接口实现类
 **/
@Service
public class TrafficServiceImpl implements TrafficService {
    /**
     * mapper存储
     */
    @Autowired
    private TrafficMapper mapper;

    /**
     * json转换
     */
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 线程池
     */
    private final ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(10, 20, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

    /**
     * 网络输入流量
     */
    private Traffic byteTraffic = new Traffic();

    /**
     * 网络输出流量
     */
    private Traffic packTraffic = new Traffic();

    /**
     * 每分钟总流量
     */
    private static AtomicReference<Double> flowPerMinute = new AtomicReference<Double>(0D);

    /**
     * 每分钟获取次数
     */
    private static AtomicInteger count = new AtomicInteger(0);

    /**
     * 定时器
     */
    private final ScheduledExecutorService service = new ScheduledThreadPoolExecutor(10);

    @PostConstruct
    @Override
    public void getInstantByteRate() {
        poolExecutor.execute(() -> {
            while (true) {
                try {
                    String rate = NetflowUtils.bytesPortInputLastSecond("", SFlowStatistic.SUM);
                    JsonNode node = objectMapper.readTree(rate).get(0);
                    byteTraffic.setMetricValue(node.get("metricValue").toString());
                    synchronized (flowPerMinute.getClass()) {
                        flowPerMinute.set(flowPerMinute.get() + Double.parseDouble(byteTraffic.getMetricValue()));
                        count.incrementAndGet();
                    }
                    Thread.sleep(800);
                } catch (InterruptedException | JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void getAllByteRate(WebSocketSession session) {
        poolExecutor.execute(() -> {
            while (session.isOpen()) {
                try {
                    session.sendMessage(new TextMessage(objectMapper.writeValueAsString(byteTraffic)));
                    Thread.sleep(1000);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @PostConstruct
    @Override
    public void getInstantPackRate() {
        poolExecutor.execute(() -> {
            while (true) {
                try {
                    String rate = NetflowUtils.pktsPortInputLastSecond("", SFlowStatistic.SUM);
                    JsonNode node = objectMapper.readTree(rate).get(0);
                    packTraffic.setMetricValue(node.get("metricValue").toString());
                    Thread.sleep(800);
                } catch (InterruptedException | JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @PostConstruct
    @Override
    public void storageTimingTask() {
        LocalDateTime time = LocalDateTime.now();
        LocalDateTime dateTime = time.withSecondOfMinute(0).withMinuteOfHour(time.getMinuteOfHour() + 1);
        Duration duration = new Duration(time.toDateTime(), dateTime.toDateTime());
        long delay = duration.getMillis();
        service.scheduleAtFixedRate(() -> {
            try {
                double rate;
                Integer num;
                synchronized (flowPerMinute.getClass()) {
                    rate = flowPerMinute.getAndSet(0D);
                    num = count.getAndSet(0);
                }
                rate /= num;
                mapper.insertTraffic(rate);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, delay, 60000, TimeUnit.MILLISECONDS);
    }

    @Override
    public void getAllPackRate(WebSocketSession session) {
        poolExecutor.execute(() -> {
            while (session.isOpen()) {
                try {
                    session.sendMessage(new TextMessage(objectMapper.writeValueAsString(packTraffic)));
                    Thread.sleep(1000);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void getPortByteRate(WebSocketSession session, String port) {
        poolExecutor.execute(() -> {
            while (session.isOpen()) {
                getPortInputByteRate(session, NetflowUtils.datasourceOfSwitchPort(port));
                getPortOutputByteRate(session, NetflowUtils.datasourceOfSwitchPort(port));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void getPortPackRate(WebSocketSession session, String port) {
        poolExecutor.execute(() -> {
            while (session.isOpen()) {
                getPortInputPackRate(session, NetflowUtils.datasourceOfSwitchPort(port));
                getPortOutputPackRate(session, NetflowUtils.datasourceOfSwitchPort(port));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void getPortInputByteRate(WebSocketSession session, String port) {
        try {
            Traffic traffic = new Traffic();
            JsonNode node = objectMapper.readTree(NetflowUtils.bytesPortInputLastSecond(port));
            traffic.setAgent(node.get(0).get("agent").toString());
            traffic.setMetricValue(node.get(0).get("metricValue").toString());
            traffic.setLastUpdate(node.get(0).get("lastUpdate").toString());
            traffic.setDirection("in");
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(traffic)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getPortInputPackRate(WebSocketSession session, String port) {
        try {
            Traffic traffic = new Traffic();
            JsonNode node = objectMapper.readTree(NetflowUtils.pktsPortInputLastSecond(port));
            traffic.setAgent(node.get(0).get("agent").toString());
            traffic.setMetricValue(node.get(0).get("metricValue").toString());
            traffic.setLastUpdate(node.get(0).get("lastUpdate").toString());
            traffic.setDirection("in");
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(traffic)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getPortOutputPackRate(WebSocketSession session, String port) {
        try {
            Traffic traffic = new Traffic();
            JsonNode node = objectMapper.readTree(NetflowUtils.pktsPortOutputLastSecond(port));
            traffic.setAgent(node.get(0).get("agent").toString());
            traffic.setMetricValue(node.get(0).get("metricValue").toString());
            traffic.setLastUpdate(node.get(0).get("lastUpdate").toString());
            traffic.setDirection("out");
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(traffic)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getPortOutputByteRate(WebSocketSession session, String port) {
        try {
            Traffic traffic = new Traffic();
            JsonNode node = objectMapper.readTree(NetflowUtils.bytesPortOutputLastSecond(port));
            traffic.setAgent(node.get(0).get("agent").toString());
            traffic.setMetricValue(node.get(0).get("metricValue").toString());
            traffic.setLastUpdate(node.get(0).get("lastUpdate").toString());
            traffic.setDirection("out");
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(traffic)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getPastByteRate(WebSocketSession session) {
        ArrayList<String> result = new ArrayList<>();
        LocalDateTime nowTime = LocalDateTime.now();
        LocalDateTime pastTime = nowTime.minusDays(2);
        List<Traffic> trafficList = null;
        Double hourPerRate = new Double(0);
        LocalDateTime tempTime = pastTime;
        int count = 0;
        while (true) {
            tempTime = tempTime.plusHours(1);
            if (tempTime.isBefore(nowTime)) {
                trafficList = mapper.getTraffic(tempTime.toString("yyyy-MM-dd HH:mm:ss"), pastTime.toString("yyyy-MM-dd HH:mm:ss"));
                for (Traffic traffic : trafficList) {
                    hourPerRate += Double.parseDouble(traffic.getMetricValue());
                    count++;
                }
                if (trafficList.size() != 0) {
                    result.add("{'metricValue'='" + hourPerRate / count + "','time'='" + trafficList.get(trafficList.size() - 1).getLastUpdate() + "'}");
                } else if (session.isOpen()) {
                    result.add("{'metricValue'='" + 0 + "','time'='" + tempTime.toString("yyyy-MM-dd HH:mm:ss") + "'}");
                }
                count = 0;
                hourPerRate = 0D;
                pastTime = pastTime.plusHours(1);
            } else {
                trafficList = mapper.getTraffic(nowTime.toString("yyyy-MM-dd HH:mm:ss"), tempTime.minusHours(1).toString("yyyy-MM-dd HH:mm:ss"));
                for (Traffic traffic : trafficList) {
                    hourPerRate += Double.parseDouble(traffic.getMetricValue());
                    count++;
                }
                if (trafficList.size() != 0) {
                    result.add("{'metricValue'='" + hourPerRate / count + "','time'='" + trafficList.get(trafficList.size() - 1).getLastUpdate() + "'}");
                } else {
                    result.add("{'metricValue'='" + 0 + "','time'='" + nowTime.toString("yyyy-MM-dd HH:mm:ss") + "'}");
                }
                break;
            }
        }
        if (session.isOpen()) {
            try {
                session.sendMessage(new TextMessage(objectMapper.writeValueAsString(result)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
