package week5.problem44;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * 用byte buddy实现一个aop
 *
 * @author : ocean_wll
 * @since 2021/5/29
 */
public class ByteBuddyAopDemo {

    public static void main(String[] args) throws Exception {
        DynamicType.Unloaded<?> dynamicType = new ByteBuddy()
                .subclass(Service.class)
                .method(ElementMatchers.any())
                .intercept(MethodDelegation.to(OceanMonitor.class))
                .make();

        Class<?> clazz = dynamicType.load(ByteBuddyAopDemo.class.getClassLoader()).getLoaded();

        clazz.getMethod("method", int.class).invoke(clazz.newInstance(), 10);
    }
}

