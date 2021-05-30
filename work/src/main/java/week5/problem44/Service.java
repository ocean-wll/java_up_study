package week5.problem44;

import java.util.concurrent.TimeUnit;

/**
 * @author : ocean_wll
 * @since 2021/5/29
 */
public class Service {

    public int method(int val) throws InterruptedException {
        System.out.println("method1: " + val);
        TimeUnit.SECONDS.sleep(1L);
        return val * val;
    }
}
