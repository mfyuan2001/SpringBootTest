package cn.com.springday3jdbc.pojo;


import cn.com.springday3jdbc.component.convert.SexConverts;

import javax.persistence.*;

@Entity(name = "user")
@Table(name = "t_uesr")
public class User {

    private long id;
    private String userName;
    private SexEnum sex;
    private String note;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    @Convert(converter = SexConverts.class)
    @Column(name = "sex")
    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }


    @Column(name = "note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
