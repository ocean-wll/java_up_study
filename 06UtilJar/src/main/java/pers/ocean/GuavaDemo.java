package pers.ocean;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.*;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author : ocean_wll
 * @since 2021/6/16
 */
public class GuavaDemo {

    static EventBus bus = new EventBus();

    static {
        bus.register(new GuavaDemo());
    }

    public static void main(String[] args) {
        List<String> lists = testString();

        List<Integer> list = testList();

        testMap(list);

        testBiMap(lists);

        testEventBus();
    }

    private static List<String> testString() {

        List<String> lists = Lists.newArrayList("a", "b", "c", "d", "e", "f");

        // 将list转成String，并可以指定分隔符拼接
        String result = Joiner.on(",").join(lists);
        System.out.println(result);

        String test = "a,d,f,,,da,gd,c";
        lists = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(test);
        System.out.println(lists);
        return lists;
    }

    private static List<Integer> testList() {
        List<Integer> list = Lists.newArrayList(1, 23, 5, 31, 53, 5);
        List<List<Integer>> list1 = Lists.partition(list, 3);

        print(list1);
        return list;
    }

    private static void testMap(List<Integer> list) {
        Multimap<Integer, Integer> bMultimap = ArrayListMultimap.create();
        list.forEach(a -> bMultimap.put(a, a + 1));
        print(bMultimap);
    }

    private static void testBiMap(List<String> lists) {
        BiMap<String, Integer> words = HashBiMap.create();
        words.put("first", 1);
        words.put("second", 2);
        words.put("third", 3);

        System.out.println(words.get("second").intValue());
        System.out.println(words.inverse().get(2));

        Map<String, String> map1 = Maps.toMap(lists.listIterator(), a -> a + "-value");
        print(map1);
    }

    private static void testEventBus() {
        AEvent event = new AEvent("ocean");
        bus.post(event);
    }

    private static void print(Object obj) {
        System.out.println(JSON.toJSONString(obj));
    }

    @Data
    @AllArgsConstructor
    public static class AEvent {
        private String name;
    }

    @Subscribe
    public void handle(AEvent event) {
        System.out.println(Thread.currentThread().getName() + " : " + event.name);
    }
}
