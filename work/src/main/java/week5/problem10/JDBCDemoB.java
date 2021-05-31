package week5.problem10;

import java.sql.*;

/**
 * @author : ocean_wll
 * @since 2021/5/31
 */
public class JDBCDemoB {

    public static void main(String[] args) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // 1、注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 2、获取连接
            String url = "jdbc:mysql://127.0.0.1:3306/ocean";
            String userName = "root";
            String password = "root";

            connection = DriverManager.getConnection(url, userName, password);

            // 3、获取statement
            preparedStatement = connection.prepareStatement("insert into user(name,age) values (?,?)");

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
        } finally {
            //5.关闭资源
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
