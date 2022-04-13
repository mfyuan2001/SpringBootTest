package cn.com.springday3jdbc.service.impl;

import cn.com.springday3jdbc.service.JDBCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author yuanmengfan
 * @date 2022/4/12 11:19 下午
 * @description
 */
@Transactional
public class JDBCServiceImpl implements JDBCService {


    @Autowired
    DataSource dataSource;

    @Override
    public int queryDate() {
        int result = 0;
        Connection connection = null;
        try {
            // 获取数据链接
            connection = dataSource.getConnection();
            // 取消自动提交 开启事务
            connection.setAutoCommit(false);
            // 设置隔离级别
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            // 执行sql
            PreparedStatement preparedStatement = connection.prepareStatement("insert into t_uesr(user_name, sex, note) value (?,?,?)");

            preparedStatement.setString(1, "111");
            preparedStatement.setInt(2, 1);
            preparedStatement.setString(3, "note");
            result = preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
