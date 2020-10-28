package com.stream.D04map操作;

import com.stream.D03filter与谓语逻辑.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * map函数的作用就是
 * 针对管道流中的每一个数据元素进行转换操作
 */
public class Demo1 {

    //将集合中的每一个字符串，全部转换成大写！
    @Test
    public void test1(){
        List<String> alpha = Arrays.asList("Monkey", "Lion", "Giraffe", "Lemur");

        // 不使用Stream管道流(就是新建一个list接收)
        List<String> alphaUpper = new ArrayList<>();
        for (String s : alpha) {
            alphaUpper.add(s.toUpperCase());
        }
        //[MONKEY, LION, GIRAFFE, LEMUR]
        System.out.println(alphaUpper);

        // 使用Stream管道流
        List<String> collect = alpha.stream()
//                .map(s -> s.toUpperCase())
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(collect);
    }

    @Test
    public void test02(){
        List<String> alpha = Arrays.asList("Monkey", "Lion", "Giraffe", "Lemur");
        // 处理非字符串类型集合元素
        List<Integer> lengths = alpha.stream()
//                .map(s -> s.length())
                .map(String::length)
                .collect(Collectors.toList());
        //[6, 4, 7, 5]
        System.out.println(lengths);

        Stream.of("Monkey", "Lion", "Giraffe", "Lemur")
                //mapToInt、maoToLong、mapToDouble
                .mapToInt(String::length)
                .forEach(System.out::println);
    }

    //处理对象数据格式转换
    @Test
    public void test03(){
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

        //将每一个Employee的年龄增加一岁
        //将性别中的“M”换成“male”，F换成Female。
        List<Employee> maped = employees.stream()
                .map(e -> {
                    e.setAge(e.getAge() + 1);
                    e.setGender(e.getGender().equals("M")?"male":"female");
                    return e;
                }).collect(Collectors.toList());

        // 由于map的参数e就是返回值，所以可以用peek函数。peek函数是一种特殊的map函数，
        // 当函数没有返回值或者参数就是返回值的时候可以使用peek函数。
        List<Employee> maped01 = employees.stream()
                .peek(e -> {
                    e.setAge(e.getAge() + 1);
                    e.setGender(e.getGender().equals("M")?"male":"female");
                }).collect(Collectors.toList());
        System.out.println(maped);
    }

    // flatMap
    // map可以对管道流中的数据进行转换操作，但是如果管道中还有管道该如何处理
    // 如何处理二维数组及二维集合类。实现一个简单的需求：
    // 将“hello”，“world”两个字符串组成的集合，元素的每一个字母打印出来。
    // 如果不用Stream我们怎么写？
    // 写2层for循环,第一层遍历字符串，并且将字符串拆分成char数组，
    // 第二层for循环遍历char数组。
    @Test
    public void test04(){
        List<String> words = Arrays.asList("hello", "word");
        words.stream()
            // [[h,e,l,l,o],[w,o,r,l,d]]
            .map(w -> Arrays.stream(w.split("")))
            .forEach(System.out::println);
        //java.util.stream.ReferencePipeline$Head@3551a94
        //java.util.stream.ReferencePipeline$Head@531be3c5

        words.stream()
                // [h,e,l,l,o,w,o,r,l,d]
                .flatMap(w -> Arrays.stream(w.split("")))
                .forEach(System.out::println);
        // h e l l o w o r d

    }

    @Test
    public void test05(){}
}
