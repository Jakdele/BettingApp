package pl.coderslab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class BettingAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BettingAppApplication.class, args);
    }
}
