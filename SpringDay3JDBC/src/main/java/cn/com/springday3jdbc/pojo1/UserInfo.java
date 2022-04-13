package cn.com.springday3jdbc.pojo1;

import cn.com.springday3jdbc.component.convert.SexConverts;
import cn.com.springday3jdbc.pojo.SexEnum;
import org.apache.ibatis.type.Alias;

import javax.persistence.Convert;

/**
 * @author yuanmengfan
 * @date 2022/4/6 11:24 下午
 * @description
 */
@Alias("user_info")
public class UserInfo {
    private long id;
    private String userName;
    private SexEnum sex;
    private String note;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
