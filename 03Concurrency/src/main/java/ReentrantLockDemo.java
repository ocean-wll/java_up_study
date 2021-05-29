import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : ocean_wll
 * @since 2021/5/22
 */
public class ReentrantLockDemo {

    private static final ReentrantLock lock = new ReentrantLock(true);

    private static final AtomicInteger start = new AtomicInteger(1);

    private static final AtomicInteger end = new AtomicInteger(100);

    public static void main(String[] args) {
        int i;
        byte b;
        boolean boo;
        short s;
        float f;
        double d;
        char c;
        long l;

        int num = 100_0000;
        System.out.println(num);

        Runnable runnable = () -> {
            for (; ; ) {
                lock.lock();
                try {
                    if (start.get() <= end.get()) {
                        System.out.println(Thread.currentThread().getName() + " print num: " + start.get());
                        start.incrementAndGet();
                    } else {
                        System.exit(0);
                    }
                } finally {
                    lock.unlock();
                }
            }
        };
        new Thread(runnable).start();
        new Thread(runnable).start();
    }
}
