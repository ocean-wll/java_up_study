package pers.ocean;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.omg.CORBA.SystemException;

import javax.transaction.UserTransaction;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author : ocean_wll
 * @since 2021/6/27
 */
public class AtomikosExample {
    private static AtomikosDataSourceBean createAtomikosDataSourceBean(String dbName) {
        // 连接池基本属性
        Properties p = new Properties();
        p.setProperty("url", "jdbc:mysql://localhost:3306/" + dbName);
        p.setProperty("user", "root");
        p.setProperty("password", "W!l123456");

        // 使用AtomikosDataSourceBean封装com.mysql.jdbc.jdbc2.optional.MysqlXADataSource
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        //atomikos要求为每个AtomikosDataSourceBean名称，为了方便记忆，这里设置为和dbName相同
        ds.setUniqueResourceName(dbName);
        ds.setXaDataSourceClassName("com.mysql.cj.jdbc.MysqlXADataSource");
        ds.setXaProperties(p);
        return ds;
    }

    public static void main(String[] args) {

        AtomikosDataSourceBean ds1 = createAtomikosDataSourceBean("ocean_db_demo_0");
        AtomikosDataSourceBean ds2 = createAtomikosDataSourceBean("ocean_db_demo_1");

        Connection conn1 = null;
        Connection conn2 = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;

        UserTransaction userTransaction = new UserTransactionImp();
        try {
            // 开启事务
            userTransaction.begin();

            // 执行db1上的sql
            conn1 = ds1.getConnection();
            ps1 = conn1.prepareStatement("INSERT into ocean_demo_order_0(user_id,status) VALUES (?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps1.setLong(1, 4);
            ps1.setString(2, "fail");
            ps1.executeUpdate();
            ResultSet generatedKeys = ps1.getGeneratedKeys();
            int userId = -1;
            while (generatedKeys.next()) {
                userId = generatedKeys.getInt(1);// 获得自动生成的userId
            }

            // 模拟异常 ，直接进入catch代码块，2个都不会提交
//        int i=1/0;

            // 执行db2上的sql
            conn2 = ds2.getConnection();
            ps2 = conn2.prepareStatement("INSERT into ocean_demo_order_0(user_id,status) VALUES (?,?)");
            ps2.setLong(1, 5);
            ps2.setString(2, "fail");
            ps2.executeUpdate();

            // 两阶段提交
            userTransaction.commit();
        } catch (Exception e) {
            try {
                e.printStackTrace();
                userTransaction.rollback();
            } catch (SystemException | javax.transaction.SystemException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                ps1.close();
                ps2.close();
                conn1.close();
                conn2.close();
                ds1.close();
                ds2.close();
            } catch (Exception ignore) {
            }
        }
    }
}
