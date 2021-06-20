package pers.ocean.dynamicdatabase.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author : ocean_wll
 * @since 2021/6/20
 */
@Mapper
public interface TestMapper {

    @Select("select * from t1")
    List<Map<String, Object>> list();
}
