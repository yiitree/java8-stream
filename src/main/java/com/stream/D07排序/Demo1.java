package com.stream.D07排序;

import com.stream.D03filter与谓语逻辑.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: 曾睿
 * @Date: 2020/10/28 16:10
 */
public class Demo1 {

    @Test
    public void demo(){
        List<String> cities = Arrays.asList(
                "Milan",
                "london",
                "San Francisco",
                "Tokyo",
                "New Delhi"
        );
        System.out.println(cities);
        //[Milan, london, San Francisco, Tokyo, New Delhi]

        // 字母大小写不敏感排序
        cities.sort(String.CASE_INSENSITIVE_ORDER);
        System.out.println(cities);
        //[london, Milan, New Delhi, San Francisco, Tokyo]

        // 字母自然顺序排序
        cities.sort(Comparator.naturalOrder());
        System.out.println(cities);
        //[Milan, New Delhi, San Francisco, Tokyo, london]


        // 排序结合stream()使用
        cities.stream().sorted(Comparator.naturalOrder()).forEach(System.out::println);

        //整数类型List排序
        List<Integer> numbers = Arrays.asList(6, 2, 1, 4, 9);
        //[6, 2, 1, 4, 9]
        System.out.println(numbers);

        //自然排序
        numbers.sort(Comparator.naturalOrder());
        //[1, 2, 4, 6, 9]
        System.out.println(numbers);

        //倒序排序
        numbers.sort(Comparator.reverseOrder());
        //[9, 6, 4, 2, 1]
        System.out.println(numbers);

        // 按对象字段对List<Object>排序
        Employee e1 = new Employee(1,23,"M","Rick","Beethovan");
        Employee e2 = new Employee(2,13,"F","Martina","Hengis");
        Employee e3 = new Employee(3,43,"M","Ricky","Martin");
        Employee e4 = new Employee(4,26,"M","Jon","Lowman");
        Employee e5 = new Employee(5,19,"F","Cristine","Maria");
        Employee e6 = new Employee(6,15,"M","David","Feezor");
        Employee e7 = new Employee(7,68,"F","Melissa","Roy");
        Employee e8 = new Employee(8,79,"M","Alex","Gussin");
        Employee e9 = new Employee(9,15,"F","Neetu","Singh");
        Employee e10 = new Employee(10,45,"M","Naveen","Jain");
        List<Employee> employees = Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10);

        // 根据年龄排序
        employees.sort(Comparator.comparing(Employee::getAge));

        // 根据年龄倒序
        employees.sort(Comparator.comparing(Employee::getAge).reversed());

        //Comparator链对List<Object>排序
        //都是正序 ，不加reversed
        //都是倒序，最后面加一个reserved
        //先是倒序（加reserved），然后正序
        //先是正序（加reserved），然后倒序（加reserved）
        employees.sort(
                Comparator.comparing(Employee::getGender)
                        .thenComparing(Employee::getAge)
                        .reversed()
        );
        employees.forEach(System.out::println);
    }

}
