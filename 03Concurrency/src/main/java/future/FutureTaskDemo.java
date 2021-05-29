package future;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author : ocean_wll
 * @since 2021/5/23
 */
public class FutureTaskDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(() -> new Random().nextInt());
        new Thread(task).start();
        System.out.println("result: " + task.get());
    }
}
