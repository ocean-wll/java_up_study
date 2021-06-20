package pers.ocean.dynamicdatabase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.ocean.dynamicdatabase.annotation.DS;
import pers.ocean.dynamicdatabase.constant.DataSourceConstant;
import pers.ocean.dynamicdatabase.mapper.TestMapper;

/**
 * @author : ocean_wll
 * @since 2021/6/20
 */
@Service
public class TestService {

    @Autowired
    private TestMapper testMapper;

    @DS()
    public void testDataSource3316() {
        System.out.println(testMapper.list());
    }

    @DS(DataSourceConstant.DATA_SOURCE_3326)
    public void testDataSource3326() {
        System.out.println(testMapper.list());
    }
}
