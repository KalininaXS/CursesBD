package Kafka.Producer;

import Kafka.util.Measurement;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class Producer {
    @Value("${topic.name.producer}")
    private static String topicName;

    private static ObjectMapper objectMapper;
    private static KafkaTemplate<String, String> kafkaTemplate;

    public static String send(Measurement measurement) throws JsonProcessingException {
        String orderAsMessage = objectMapper.writeValueAsString(measurement);
        kafkaTemplate.send(topicName, orderAsMessage);

        log.info("food order produced {}", orderAsMessage);

        return "message sent";
    }
}
