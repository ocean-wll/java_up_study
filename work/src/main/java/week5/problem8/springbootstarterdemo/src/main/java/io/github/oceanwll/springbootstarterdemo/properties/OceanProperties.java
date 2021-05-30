package io.github.oceanwll.springbootstarterdemo.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author : ocean_wll
 * @since 2021/5/30
 */
@ConfigurationProperties("ocean")
public class OceanProperties {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
