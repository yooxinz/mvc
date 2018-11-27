package com.yooxinz.dto;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by star on 2018/9/23.
 */
public class User {

    private Long id;

    private String name;

    private Date birthday;

    private Boolean state;

    private Timestamp create_date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Timestamp getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Timestamp create_date) {
        this.create_date = create_date;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", state=" + state +
                ", create_date=" + create_date +
                '}';
    }
}
