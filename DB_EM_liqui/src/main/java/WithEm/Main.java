package WithEm;



import WithEm.Service.StudentsService;
import lombok.SneakyThrows;
import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.sql.SQLException;


@SpringBootApplication
@EnableJpaRepositories
public class Main {
    @SneakyThrows
    public static void main(String[] args) throws SQLException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Main.class, args);

        StudentsService studentsService = applicationContext.getBean(StudentsService.class);
        Iterable<Double> marked = studentsService.AvgMark();
        System.out.println(marked);


        Console.main(args);
    }
}