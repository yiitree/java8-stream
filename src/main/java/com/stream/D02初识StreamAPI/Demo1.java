package com.stream.D02初识StreamAPI;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * 把 数组、集合、文本文件 转化为管道流
 */
public class Demo1 {

    /**
     * 案例
     */
    @Test
    public void demo01(){
        List<String> nameStrs = Arrays.asList("Monkey", "Lion", "Giraffe","Lemur");

        List<String> list = nameStrs
                //我们使用Stream()函数，将一个List转换为管道流
                .stream()
                //调用filter函数过滤数组元素，过滤方法使用lambda表达式，以L开头的元素返回true被保留，其他的List元素被过滤掉
                .filter(s -> s.startsWith("L"))
                //然后调用Map函数对管道流中每个元素进行处理，字母全部转换为大写
                .map(String::toUpperCase)
                //然后调用sort函数，对管道流中数据进行排序
                .sorted()
                //最后调用collect函数toList，将管道流转换为List返回
                .collect(toList());
        //[LEMUR, LION]
        System.out.println(list);

    }


    /**
     * 一、将数组转换为管道流
     */
    @Test
    public void demo02(){
        String[] array = {"Monkey", "Lion", "Giraffe", "Lemur"};
        Stream<String> nameStrs2 = Stream.of(array);
        Stream<String> nameStrs3 = Stream.of("Monkey", "Lion", "Giraffe", "Lemur");
    }

    /**
     * 二、将集合类对象转换为管道流
     */
    @Test
    public void demo3(){
        List<String> list = Arrays.asList("Monkey", "Lion", "Giraffe", "Lemur");
        Stream<String> streamFromList = list.stream();

        Set<String> set = new HashSet<>(list);
        Stream<String> streamFromSet = set.stream();
    }


    /**
     * 三、将文本文件转换为管道流
     */
    @Test
    public void demo4() throws IOException {
        Stream<String> lines = Files.lines(Paths.get("file.txt"));
    }

}
