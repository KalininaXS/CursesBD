package Kafka.Consumer.Service;

import Kafka.Repository.Repository;
import Kafka.util.Measurement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class Service_Consumer {
    private final Repository Repository;

    public void saveMeasuremet(Measurement measurement) {
        //Order_domain Order = modelMap.map(order, Order_domain.class);
        Measurement saved_measurement = Repository.save(measurement);

        log.info("food order persisted {}", saved_measurement);
    }
}
