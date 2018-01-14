package top.ball.rice.hospital.service;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "top.ball.rice.hospital")
@Component
public class HospitalProperties {


}
