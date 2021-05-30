package week5.problem44;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * https://bytebuddy.net/ bytebuddy 官网教学
 *
 * @author : ocean_wll
 * @since 2021/5/29
 */
public class ByteBuddyHelloWord {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {

        // 利用ByteBuddy将 dynamicType 的 toString返回值指定为 hello word
        Class<?> dynamicType = new ByteBuddy()
                .subclass(Object.class)
                .method(ElementMatchers.named("toString"))
                .intercept(FixedValue.value("Hello World!"))
                .make()
                .load(ByteBuddyHelloWord.class.getClassLoader())
                .getLoaded();

        System.out.println(dynamicType.newInstance());
    }
}
