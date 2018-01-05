package net.kingsilk.qh.hospital.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 */
@SpringBootApplication(scanBasePackages = {"net.kingsilk.qh.bargain"})
public class HospitalServerApp {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(HospitalServerApp.class, args);
    }

}
