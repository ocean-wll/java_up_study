package week4.problem1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : ocean_wll
 * @since 2021/5/24
 */
public class AsyncDemo {
    private final static ExecutorService executor = Executors.newFixedThreadPool(8);

    private static String globalResult;

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();

        // 方法一
//        Future<String> future = executor.submit(() -> "ocean wll");
//        String result = future.get();

        // 方法二
//        FutureTask<String> futureTask = new FutureTask<>(() -> "ocean wll");
//        Thread thread = new Thread(futureTask);
//        thread.start();
//        String result = futureTask.get();

        // 方法三
//        String result = CompletableFuture.supplyAsync(() -> "ocean wll").get();

        // 方法四
        Thread thread = new Thread(() -> globalResult = "ocean wll");
        thread.start();
        thread.join();
        String result = globalResult;

        System.out.println("异步计算结果为：" + result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        System.exit(0);
    }
}
