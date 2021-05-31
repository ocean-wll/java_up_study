package week5.problem10;

import java.sql.*;

/**
 * @author : ocean_wll
 * @since 2021/5/31
 */
public class JDBCDemoA {

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
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
            statement = connection.createStatement();

            // 4、执行sql
            // 新增
            statement.execute("INSERT INTO user(name,age) values ('ocean',25)");
            // 修改
            statement.execute("UPDATE user SET age=30 WHERE name = 'ocean'");
            // 查询
            resultSet = statement.executeQuery("SELECT * FROM USER");
            while (resultSet.next()) {
                //4.2封装对象
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                System.out.println("id: " + id + " ,name: " + name + " ,age: " + age);
            }
            // 删除
            statement.execute("DELETE FROM user WHERE name = 'ocean'");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5.关闭资源
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
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
