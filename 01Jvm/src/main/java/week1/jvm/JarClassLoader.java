package week1.jvm;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 自定义加载classLoader
 *
 * @author : ocean_wll
 * @since 2021/5/9
 */
public class JarClassLoader extends URLClassLoader {

    public JarClassLoader(URL[] urls) {
        super(urls);
    }

    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        String filePath = JarClassLoader.class.getClassLoader().getResource("fastjson-1.2.62.jar").getPath();
        URL[] urls = new URL[]{};
        JarClassLoader jarClassLoader = new JarClassLoader(urls);
        jarClassLoader.addURL(new File(filePath).toURI().toURL());
        Class jsonObjectClass = jarClassLoader.findClass("com.alibaba.fastjson.JSONObject");

        Object jsonObject = jsonObjectClass.newInstance();

    }
}
