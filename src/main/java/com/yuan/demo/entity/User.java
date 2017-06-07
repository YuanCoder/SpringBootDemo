package com.yuan.demo.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 有时我们不愿意把配置都写到application配置文件中，这时需要我们自定义配置文件，比如test.properties
 * 将这个配置文件信息赋予给一个javabean  User
 * 在最新版本的springboot，需要加这三个注解。@Configuration
 * @PropertySource(value = “classpath:test.properties”)
 * @ConfigurationProperties(prefix = “com.forezp”);在1.4版本需要
 * PropertySource加上location。
 * Created by fangzhipeng on 2017/4/18.
 */


@Configuration
@PropertySource(value = "classpath:test.properties")
@ConfigurationProperties(prefix = "com.forezp")
public class User {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
