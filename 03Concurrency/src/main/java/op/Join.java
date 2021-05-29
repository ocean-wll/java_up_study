package op;

/**
 * @author : ocean_wll
 * @since 2021/5/23
 */
public class Join {

    public static void main(String[] args) {
        Object obj = new Object();

        MyThread thread = new MyThread("mythead -->");
        thread.setObj(obj);
        thread.start();

        synchronized (obj) {
            for (int i = 0; i < 100; i++) {
                if (i == 20) {
                    try {
                        obj.wait(0); // wait 会释放锁
//                        thread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " -- " + i);
            }
        }
    }
}

class MyThread extends Thread {
    private String name;

    private Object obj;

    public void setObj(Object o) {
        this.obj = o;
    }

    public MyThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        synchronized (obj) {
            for (int i = 0; i < 100; i++) {
                System.out.println(name + i);
            }
        }
    }
}
