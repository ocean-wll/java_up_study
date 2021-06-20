package pers.ocean.shardingshperedemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.ocean.shardingshperedemo.Mapper.TestMapper;
import pers.ocean.shardingshperedemo.model.T1DO;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author : ocean_wll
 * @since 2021/6/20
 */
@RestController
public class TestController {

    @Autowired
    private TestMapper testMapper;

    @GetMapping("/create")
    public String create() {
        Random rand = new Random();
        int randomNum = rand.nextInt(100);
        System.out.println(randomNum);
        T1DO t1DO = new T1DO(randomNum);
        testMapper.create(t1DO);
        return "success";
    }


    @GetMapping("/list")
    public List<Map<String, Object>> list() {
        return testMapper.list();
    }
}
