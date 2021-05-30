package week5.problem45;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;
import week5.problem44.OceanMonitor;

import java.lang.instrument.Instrumentation;

/**
 * 自定义拦截类
 *
 * @author : ocean_wll
 * @since 2021/5/29
 */
public class MyAgent {

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("this is an perform monitor agent.");

        AgentBuilder.Transformer transformer = (builder, typeDescription, classLoader, javaModule) -> builder
                // 拦截任意方法
                .method(ElementMatchers.any())
                // 委托
                .intercept(MethodDelegation.to(OceanMonitor.class));

        AgentBuilder.Listener listener = new AgentBuilder.Listener() {
            @Override
            public void onDiscovery(String s, ClassLoader classLoader, JavaModule javaModule, boolean b) {

            }

            @Override
            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean b, DynamicType dynamicType) {

            }

            @Override
            public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean b) {

            }

            @Override
            public void onError(String s, ClassLoader classLoader, JavaModule javaModule, boolean b, Throwable throwable) {

            }

            @Override
            public void onComplete(String s, ClassLoader classLoader, JavaModule javaModule, boolean b) {

            }
        };

        new AgentBuilder
                .Default()
                // 指定需要拦截的类
                .type(ElementMatchers.nameStartsWith("week5.problem45"))
                .transform(transformer)
                .with(listener)
                .installOn(inst);
    }
}
