package future;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author : ocean_wll
 * @since 2021/5/23
 */
public class FutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(8);
        Future<Integer> future = executor.submit(() -> new Random().nextInt());
        System.out.println("result: " + future.get());
    }
}
