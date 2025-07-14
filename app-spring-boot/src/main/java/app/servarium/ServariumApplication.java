package app.servarium;

import app.servarium.adapter.security.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableConfigurationProperties(value = JwtProperties.class)
public class ServariumApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServariumApplication.class, args);
    }
}
