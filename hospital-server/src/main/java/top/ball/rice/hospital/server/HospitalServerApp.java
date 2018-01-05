package top.ball.rice.hospital.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 */
@SpringBootApplication(scanBasePackages = {"top.ball.rice.hospital"})
//@EnableJpaRepositories
public class HospitalServerApp {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(HospitalServerApp.class, args);
    }

}
