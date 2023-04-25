package Kafka.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serializer;

@Slf4j
public class CustomSerializer  implements Serializer<Measurement> {
    @Override
    public byte[] serialize(String s, Measurement measurement) {
        ObjectMapper objectMapper = new ObjectMapper();
        if (measurement != null) {
            try {
                return objectMapper.writeValueAsBytes(measurement);
            } catch (JsonProcessingException e) {
                log.error("Unable to serialize measurement cause : {}", e.getMessage(), e);
                return new byte[0];
            }
        }
        return new byte[0];
    }
}
