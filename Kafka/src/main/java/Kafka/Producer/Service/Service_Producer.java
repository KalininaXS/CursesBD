package Kafka.Producer.Service;

import Kafka.Producer.Producer;
import Kafka.util.Measurement;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class Service_Producer {
    private final Producer producer;

    public String send(Measurement measurement) throws JsonProcessingException {
        return Producer.send(measurement);
    }

}
