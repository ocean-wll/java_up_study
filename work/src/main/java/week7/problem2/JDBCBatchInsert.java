package week7.problem2;

import java.sql.*;

/**
 * @author : ocean_wll
 * @since 2021/5/31
 */
public class JDBCBatchInsert {

    public static void main(String[] args) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // 1、注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 2、获取连接
            String url = "jdbc:mysql://127.0.0.1:3306/geek_java_up";
            String userName = "root";
            String password = "W!l123456";

            connection = DriverManager.getConnection(url, userName, password);

            // 3、获取statement
            preparedStatement = connection.prepareStatement("INSERT INTO geek_java_up.order(order_id,user_id," +
                    "total_money,status,address)  value(?,?,?,1,'这是一个收货地址')");

            Long startTime = System.currentTimeMillis();
            // 关闭事务自动提交
            connection.setAutoCommit(false);
            for (int i = 0; i < 1000000; i++) {
                preparedStatement.setLong(1, i);
                preparedStatement.setLong(2, i);
                preparedStatement.setLong(3, i);
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();

            //提交事务
            connection.commit();
            Long endTime = System.currentTimeMillis();
            System.out.println("执行时间为 " +(endTime - startTime)/1000 +" s");
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
