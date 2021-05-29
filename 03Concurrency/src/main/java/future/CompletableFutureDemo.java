package future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author : ocean_wll
 * @since 2021/5/24
 */
public class CompletableFutureDemo {
    public static void main(String[] args) {
        // 1、交换结果
        System.out.println("=====> 1、交换结果");
        String result1 =
                CompletableFuture.supplyAsync(() -> "hello ").thenApplyAsync(result -> result + "word").join();
        System.out.println(result1);

        // 2、消费
        System.out.println("=====> 2、消费");
        CompletableFuture.supplyAsync(() -> "hello ").thenAccept(v -> System.out.println("消费：" + v)).join();

        // thenApplyAsync 和 thenAccept 的区别 前者有返回值，后者没有返回值

        // 3、组合
        System.out.println("=====> 3、组合");
        String result3 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello ";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "word";
        }), (t1, t2) -> t1 + t2).join();
        System.out.println("thenCombine: " + result3);

        CompletableFuture.supplyAsync(() -> "Hello, ocean wll").thenApply(String::toUpperCase).thenCompose(s ->
                CompletableFuture.supplyAsync(s::toLowerCase).thenAccept(v -> System.out.println("thenCompose: " + v))
        );

        // thenCombine和thenCompose区别：
        // thenCombine会在两个任务都执行完以后，将两个任务的结果合并
        // thenCompose可以整合多个CompletableFuture，将前一个任务的结果作为下一个的入参，它们之间有先后关系

        // 4、竞争
        System.out.println("=====> 4、竞争");
        String result4 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hi boy";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hi girl";
        }), (s) -> s).join();
        System.out.println(result4);

        // 5、异常补偿
        System.out.println("=====> 5、异常补偿");
        String result5 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (true) {
                throw new RuntimeException("Exception test");
            }
            return "hello boy";
        }).exceptionally(e -> {
            System.out.println(e.getMessage());
            return "hello word";
        }).join();
        System.out.println(result5);

    }
}
