package com.stream.D00Java8方法引用;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 曾睿
 * @Date: 2020/10/28 19:45
 */
public class Demo {
    @Test
    public void Test01() {
        List<String> names = new ArrayList();

        names.add("Google");
        names.add("Runoob");
        names.add("Taobao");
        names.add("Baidu");
        names.add("Sina");

        names.forEach(System.out::println);
    }
}

@FunctionalInterface
interface Supplier<T> {
    T get();
}

class Car {
    //Supplier是jdk1.8的接口，这里和lamda一起使用了
    public static Car create(final Supplier<Car> supplier) {
        return supplier.get();
    }

    //碰撞
    public static void collide(final Car car) {
        System.out.println("碰撞 " + car.toString());
    }

    //跟随
    public void follow(final Car another) {
        System.out.println("跟随 " + another.toString());
    }

    //维修
    public void repair() {
        System.out.println("维修 " + this.toString());
    }
}
