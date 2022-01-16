package auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author datdv
 */
@SpringBootApplication(
        scanBasePackages = {
                "com.microservices.auth",
                "com.microservices.amqp",
        }
)
@EnableFeignClients(
        basePackages = "com.microservices.clients"
)
@EnableEurekaClient
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
