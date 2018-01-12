package top.ball.rice.hospital.domain;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Treat extends Base {

    private String suffererId;

    private String doctorId;

    private Date sickDate;

    private String pathogeny;

    private String sickHistory;

    private String result;

    private String opinion;

    private String prescription;

    private String sickroomId;

    public String getSuffererId() {
        return suffererId;
    }

    public void setSuffererId(String suffererId) {
        this.suffererId = suffererId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public Date getSickDate() {
        return sickDate;
    }

    public void setSickDate(Date sickDate) {
        this.sickDate = sickDate;
    }

    public String getPathogeny() {
        return pathogeny;
    }

    public void setPathogeny(String pathogeny) {
        this.pathogeny = pathogeny;
    }

    public String getSickHistory() {
        return sickHistory;
    }

    public void setSickHistory(String sickHistory) {
        this.sickHistory = sickHistory;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getSickroomId() {
        return sickroomId;
    }

    public void setSickroomId(String sickroomId) {
        this.sickroomId = sickroomId;
    }
}
