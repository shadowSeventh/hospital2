package top.ball.rice.hospital.service;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "top.ball.rice.hospital")
@Component
public class HospitalProperties {

    private String addresses;

    private Integer waitLockTime;

    public String getAddresses() {
        return addresses;
    }

    public void setAddresses(String addresses) {
        this.addresses = addresses;
    }

    public Integer getWaitLockTime() {
        return waitLockTime;
    }

    public void setWaitLockTime(Integer waitLockTime) {
        this.waitLockTime = waitLockTime;
    }
}
