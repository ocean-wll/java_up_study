package io.ocean.entity;

import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author ocean_wll
 * @since 2021-05-30 20:26:57
 */
public class User implements Serializable {
    private static final long serialVersionUID = -98285153405232118L;

    private Integer id;

    private String name;

    private String t1;

    private String t2;

    private String t3;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getT1() {
        return t1;
    }

    public void setT1(String t1) {
        this.t1 = t1;
    }

    public String getT2() {
        return t2;
    }

    public void setT2(String t2) {
        this.t2 = t2;
    }

    public String getT3() {
        return t3;
    }

    public void setT3(String t3) {
        this.t3 = t3;
    }

}
