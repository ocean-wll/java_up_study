package pers.ocean.shardingshperedemo.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import pers.ocean.shardingshperedemo.model.T1DO;

import java.util.List;
import java.util.Map;

/**
 * @author : ocean_wll
 * @since 2021/6/20
 */
@Mapper
@Component
public interface TestMapper {

    @Select("select * from t1")
    List<Map<String, Object>> list();

    @Insert("insert into t1 values(#{id})")
    Integer create(T1DO t1DO);
}
