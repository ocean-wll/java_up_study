package sync;

import java.util.concurrent.TimeUnit;

/**
 * @author : ocean_wll
 * @since 2021/5/23
 */
public class Counter {

    public final static int A = 10;

    public static int B = 10;

    private volatile int sum = 0;

    public void incr() {
        sum += 1;
    }

    public int getSum() {
        return sum;
    }

    public static void main(String[] args) throws InterruptedException {
        int loop = 10_0000;

        Counter counter = new Counter();
        for (int i = 0; i < loop; i++) {
            counter.incr();
        }
        System.out.println("single thread: " + counter.getSum());

        final Counter counter2 = new Counter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < loop / 2; i++) {
                counter2.incr();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < loop / 2; i++) {
                counter2.incr();
            }
        });

        t1.start();
        t2.start();
        TimeUnit.SECONDS.sleep(1);

        System.out.println("multiple threads: " + counter2.getSum());
    }

}
