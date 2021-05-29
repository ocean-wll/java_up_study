import java.util.concurrent.TimeUnit;

/**
 * @author : ocean_wll
 * @since 2021/5/22
 */
public class SynchronizedDemo {

    private Integer val;

    public static void main(String[] args) throws InterruptedException {
        SynchronizedDemo demo = new SynchronizedDemo();
        System.out.println(demo.getVal());
        demo.set(1);
    }


    public synchronized void set(Integer val) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5L);
        this.val = val;
    }

    public synchronized Integer getVal(){
        return this.val;
    }
}
