package com.yuan.demo.entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Administrator on 2017/6/9 0009.
 */
public class PersonForm {

    @NotNull // 不为空
    @Size(min = 2,max = 30) // name的长度为2-30个字符
    private String name;

    @NotNull
    @Min(18) //age不能小于18
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "PersonForm{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
