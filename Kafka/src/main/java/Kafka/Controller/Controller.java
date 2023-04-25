package Kafka.Controller;

import Kafka.Consumer.Service.Service_Consumer;
import Kafka.Producer.Producer;
import Kafka.Producer.Service.Service_Producer;
import Kafka.Repository.Repository;
import Kafka.util.Measurement;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/measurement")
public class Controller {
    private final Service_Producer serviceProducer;

    private final Repository repository;

    @PostMapping(value = "/pass")
    public String send(@RequestParam Measurement measurement) throws JsonProcessingException {
        return serviceProducer.send(measurement);
    }


    @GetMapping(value = "/getmeasurement")
    public List<Measurement> get(){
        return repository.findAll();
    }

}
