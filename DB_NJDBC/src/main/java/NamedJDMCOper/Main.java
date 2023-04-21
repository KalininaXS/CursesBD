package NamedJDMCOper;

import NamedJDMCOper.Service.StudService;
import lombok.SneakyThrows;
import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.sql.SQLException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
@SpringBootApplication
@EnableJpaRepositories
public class Main {
    public static void main(String[] args) throws SQLException {

        ConfigurableApplicationContext applicationContext = SpringApplication.run(Main.class, args);

        StudService studentsService = applicationContext.getBean(StudService.class);
        Iterable<Double> marked = studentsService.AvgMarkOnCurs();
        System.out.println(marked);


        Console.main(args);
    }
}