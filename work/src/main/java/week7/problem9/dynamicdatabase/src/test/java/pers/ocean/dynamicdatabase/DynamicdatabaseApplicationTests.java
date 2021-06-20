package pers.ocean.dynamicdatabase;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pers.ocean.dynamicdatabase.constant.DataSourceConstant;
import pers.ocean.dynamicdatabase.service.TestService;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DynamicdatabaseApplication.class)
class DynamicdatabaseApplicationTests {

    @Autowired
    @Qualifier(DataSourceConstant.DATA_SOURCE_3316)
    private DataSource dataSource;

    @Autowired
    @Qualifier(DataSourceConstant.DATA_SOURCE_3326)
    private DataSource dataSource1;

    @Autowired
    private TestService testService;

    @Test
    public void method() {
        System.out.println(((HikariDataSource) dataSource).getPoolName());
        System.out.println(((HikariDataSource) dataSource1).getPoolName());
    }

    @Test
    public void method1() {
        testService.testDataSource3316();
        testService.testDataSource3326();
        testService.testDataSource3316();
        testService.testDataSource3326();
    }

}
