package cn.com.springday3jdbc.service.impl;

import cn.com.springday3jdbc.pojo.SexEnum;
import cn.com.springday3jdbc.pojo.TUesr;
import cn.com.springday3jdbc.service.JdbcTemplateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.List;

/**
 * @author yuanmengfan
 * @date 2022/4/1 9:56 下午
 * @description
 */

@Service
public class JdbcTemplateUserServiceImpl implements JdbcTemplateUserService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public RowMapper<TUesr> getUserMapping(){
        return (ResultSet rs, int rowNum) ->{
            TUesr tUesr = new TUesr();
            tUesr.setId(rs.getLong("id"));
            tUesr.setUserName(rs.getString("user_name"));
            tUesr.setSex(SexEnum.getEnumById(rs.getInt("sex")));
            tUesr.setNote(rs.getString("note"));
            return tUesr;
        };
    }


    @Override
    public TUesr getUser(Long id) {
        String sql = "select * from t_uesr where id = ?";
        TUesr tUesr = jdbcTemplate.queryForObject(sql, new Object[]{id}, getUserMapping());
        return tUesr;
    }

    @Override
    public List<TUesr> findUesrList(String name, String note) {
        String sql = "select * from t_uesr where user_name like concat('%',?,'%') and note like concat('%',?,'%') ";
        List<TUesr> query = jdbcTemplate.query(sql, getUserMapping(), name, note);
        return query;
    }

    @Override
    public int insertUser(TUesr tUesr) {
        String sql = "insert into  t_uesr (user_name, sex, note) values(?,?,?)";
        return jdbcTemplate.update(sql,tUesr.getUserName(),tUesr.getSex().getId(),tUesr.getNote());
    }

    @Override
    public int updateUser(TUesr tUesr) {
        String sql = "update t_uesr set user_name = ?,sex=?,note=? where id=?";
        return jdbcTemplate.update(sql,tUesr.getUserName(),tUesr.getSex().getId(),tUesr.getNote(),tUesr.getId());
    }

    @Override
    public int deleteUser(Long id) {
        String sql = "delete from  t_uesr where id=?";
        return jdbcTemplate.update(sql,id);
    }


}
