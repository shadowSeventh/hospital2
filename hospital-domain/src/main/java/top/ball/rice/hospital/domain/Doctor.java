package top.ball.rice.hospital.domain;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "doctor")
public class Doctor extends User {
}
