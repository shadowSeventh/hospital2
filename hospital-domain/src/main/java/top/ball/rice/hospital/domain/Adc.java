package top.ball.rice.hospital.domain;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;

/**
 * 行政区划代码
 */
@Entity
public class Adc {

    /**
     * 编码
     */
    @Id
    private String id;

    @Id
    private String no;

    /**
     * 父级行政区
     */
    private String parentId;


    /**
     * 名字
     */
    private String name;


}
