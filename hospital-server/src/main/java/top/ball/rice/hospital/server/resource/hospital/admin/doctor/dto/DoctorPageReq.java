package top.ball.rice.hospital.server.resource.hospital.admin.doctor.dto;

import top.ball.rice.hospital.server.common.UniPageReq;

import javax.ws.rs.QueryParam;

public class DoctorPageReq extends UniPageReq {

    @QueryParam(value = "keyWord")
    private String keyWord;

    @QueryParam(value = "status")
    private String status;

    @QueryParam(value = "department")
    private String department;

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
