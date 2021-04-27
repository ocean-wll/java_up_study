package jvm;

import sun.misc.Launcher;

import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

/**
 * @author : ocean_wll
 * @since 2021/4/27
 */
public class JvmClassLoaderPrintPath {

    public static void main(String[] args) {

        URL[] urls = Launcher.getBootstrapClassPath().getURLs();

        System.out.println("启动类加载器");
        for (URL url : urls) {
            System.out.println(" ==> " + url.toExternalForm());
        }

        // 扩展类加载器
        printClassLoader("扩展类加载器", JvmClassLoaderPrintPath.class.getClassLoader().getParent());

        // 应用类加载器
        printClassLoader("应用类加载器", JvmClassLoaderPrintPath.class.getClassLoader());
    }


    private static void printClassLoader(String name, ClassLoader classLoader) {
        System.out.println();
        if (classLoader != null) {
            System.out.println(name + " classLoader:" + classLoader.toString());
            printUrlForClassLoad(classLoader);
        } else {
            System.out.println(name + " classLoader: null");
        }
    }

    private static void printUrlForClassLoad(ClassLoader classLoader) {
        //ucp path
        Object ucp = insightField(classLoader, "ucp");
        Object path = insightField(ucp, "path");
        ArrayList list = (ArrayList) path;
        for (Object item : list) {
            System.out.println(" ==> " + item.toString());
        }
    }

    private static Object insightField(Object obj, String fieldName) {
        try {
            Field field;
            if (obj instanceof URLClassLoader) {
                field = URLClassLoader.class.getDeclaredField(fieldName);
            } else {
                field = obj.getClass().getDeclaredField(fieldName);
            }
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
