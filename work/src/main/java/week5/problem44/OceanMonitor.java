package week5.problem44;

import lombok.SneakyThrows;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.Callable;

/**
 * @author : ocean_wll
 * @since 2021/5/29
 */
public class OceanMonitor {

    @SneakyThrows
    @RuntimeType
    public static Object intercept(@Origin Method method, @AllArguments Object[] args,
                                   @SuperCall Callable<?> callable) {
        long start = System.currentTimeMillis();
        Object returnObj = null;
        try {
            returnObj = callable.call();
            return returnObj;
        } finally {
            System.out.println("方法名称：" + method.getName());
            System.out.println("方法入参：" + Arrays.toString(args));
            System.out.println("返回结果：" + returnObj);
            System.out.println("方法耗时：" + (System.currentTimeMillis() - start) + "ms");
        }
    }
}
