import java.util.concurrent.TimeUnit;

/**
 * @author : ocean_wll
 * @since 2021/5/23
 */
public class ThreadGroupDemo {

    public static void main(String[] args) throws InterruptedException {
        ThreadGroup threadGroup = new ThreadGroup("group");
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(threadGroup,()->{
                System.out.println(Thread.currentThread().getThreadGroup());
            });
            thread.start();
        }
        TimeUnit.SECONDS.sleep(10);
    }
}
