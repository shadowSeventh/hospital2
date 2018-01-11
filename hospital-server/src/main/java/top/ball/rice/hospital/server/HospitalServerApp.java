package top.ball.rice.hospital.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 */
@SpringBootApplication(scanBasePackages = {"top.ball.rice.hospital"})
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
public class HospitalServerApp {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(HospitalServerApp.class, args);
    }

}
