package week1;

import java.io.*;
import java.lang.reflect.Method;
import java.nio.file.Files;

/**
 * 自定义classLoader加载 hello.xlass
 *
 * @author : ocean_wll
 * @since 2021/5/1
 */
public class HelloClassLoader extends ClassLoader {

    public static void main(String[] args) {
        HelloClassLoader helloClassLoader = new HelloClassLoader();
        Class hello = helloClassLoader.findClass("Hello");
        try {
            Method method = hello.getDeclaredMethod("hello");
            method.setAccessible(true);
            method.invoke(hello.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected Class<?> findClass(String name) {
        String filePath = this.getClass().getClassLoader().getResource("Hello.xlass").getPath();
        File file = new File(filePath);

        byte[] data = new byte[(int) file.length()];
        try {
            data = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) (255 - data[i]);
        }
        return defineClass(name, data, 0, data.length);
    }
}
