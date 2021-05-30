package week5.problem45;

import java.util.concurrent.TimeUnit;

/**
 * -javaagent:/Users/ocean_wll/IdeaProjects/geekbang_java_up_study/work/target/work-1.0-SNAPSHOT.jar
 * 启动的时候加上 javaagent
 *
 * @author : ocean_wll
 * @since 2021/5/29
 */
public class OceanService2 {

    public int method1(int val) throws InterruptedException {
        System.out.println("method1: " + val);
        TimeUnit.SECONDS.sleep(1L);
        return val * val;
    }

    public int method2(int val) throws InterruptedException {
        System.out.println("method2: " + val);
        TimeUnit.SECONDS.sleep(1L);
        return val + val;
    }

    public static void main(String[] args) throws InterruptedException {
        OceanService2 service2 = new OceanService2();

        service2.method1(890);
        service2.method2(20);
    }
}
