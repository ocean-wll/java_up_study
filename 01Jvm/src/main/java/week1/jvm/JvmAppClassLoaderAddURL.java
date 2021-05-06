package week1.jvm;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 通过URLClassLoader的addUrl来加载类
 *
 * @author : ocean_wll
 * @since 2021/4/27
 */
public class JvmAppClassLoaderAddURL {

    public static void main(String[] args) {
        String path = "file:/Users/ocean_wll/IdeaProjects/geekbang_java_up_study/01Jvm/src/main/java/week1.jvm/Hello.class";
        URLClassLoader urlClassLoader = (URLClassLoader) JvmAppClassLoaderAddURL.class.getClassLoader();
        try {
            Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            method.setAccessible(true);
            URL url = new URL(path);
            method.invoke(urlClassLoader, url);
            Class.forName("week1.jvm.Hello");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    java9因为AppClassLoader、ExtClassLoader 没有继承URLClassLoader所以上面的方法会抛ClassCastException，可以使用下面的方法进行加载
//    public static void main(String[] args) {
//        String path = "file:/Users/ocean_wll/IdeaProjects/geekbang_java_up_study/01Jvm/src/main/java/week1.jvm/Hello.class";
//        try {
//            URL[] urls = new URL[1];
//            URL url = new URL(path);
//            urls[0] = url;
//            Class.forName("week1.jvm.Hello", true, new URLClassLoader(urls));
//            System.out.println(11);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
