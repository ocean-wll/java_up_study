package week5.problem10;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author : ocean_wll
 * @since 2021/5/31
 */
public class HikariDemo {

    public static void main(String[] args) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/ocean");
        config.setUsername("root");
        config.setPassword("root");
        // 连接超时：1秒
        config.addDataSourceProperty("connectionTimeout", "1000");
        // 空闲超时：60秒
        config.addDataSourceProperty("idleTimeout", "60000");
        // 最大连接数：10
        config.addDataSourceProperty("maximumPoolSize", "10");
        DataSource ds = new HikariDataSource(config);

        try (Connection connection = ds.getConnection()) {
            // 3、获取statement
            PreparedStatement preparedStatement = connection.prepareStatement("insert into user(name,age) values (?," +
                    "?)");

            // 关闭事务自动提交
            connection.setAutoCommit(false);
            for (int i = 0; i < 1000; i++) {
                preparedStatement.setString(1, "ocean" + i);
                preparedStatement.setInt(2, i);
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();

            //提交事务
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
