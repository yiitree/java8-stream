package com.stream.D05状态与并行;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: 曾睿
 * @Date: 2020/10/28 15:59
 */
public class Demo1 {

    //Limit与Skip管道数据截取
    @Test
    public void demo(){
        // limt方法传入一个整数n，用于截取管道中的前n个元素。
        // 经过管道处理之后的数据是：[Monkey, Lion]
        List<String> limitN = Stream.of("Monkey", "Lion", "Giraffe", "Lemur")
                .limit(2)
                .collect(Collectors.toList());

        // skip方法与limit方法的使用相反，用于跳过前n个元素，截取从n到末尾的元素。
        // 经过管道处理之后的数据是： [Giraffe, Lemur]
        List<String> skipN = Stream.of("Monkey", "Lion", "Giraffe", "Lemur")
                .skip(2)
                .collect(Collectors.toList());

        // 去重，equals方法进行对象的比较的，可以重写equals方法。
        // ["Monkey", "Lion", "Giraffe", "Lemur"]
        List<String> uniqueAnimals = Stream.of("Monkey", "Lion", "Giraffe", "Lemur", "Lion")
                .distinct()
                .collect(Collectors.toList());

        // Sorted排序
        // [Giraffe, Lemur, Lion, Monkey]
        List<String> alphabeticOrder = Stream.of("Monkey", "Lion", "Giraffe", "Lemur")
                .sorted()
                .collect(Collectors.toList());


    }

    //串行、并行与顺序
    @Test
    public void demo02(){
        /**
         * 串行的好处是可以保证顺序，但是通常情况下处理速度慢一些
         * 并行的好处是对于元素的处理速度快一些（通常情况下），但是顺序无法保证。
         * 这可能会导致进行一些有状态操作的时候，最后得到的不是你想要的结果。
         */

        /**
         * parallel()函数表示对管道中的元素进行并行处理，而不是串行处理。
         * 但是这样就有可能导致管道流中后面的元素先处理，前面的元素后处理，也就是元素的顺序无法保证。
         */
        Stream.of("Monkey", "Lion", "Giraffe", "Lemur", "Lion")
                .parallel()
                .forEach(System.out::println);

        /**
         * 数据源易拆分：从处理性能的角度，parallel()更适合处理ArrayList，而不是LinkedList。因为ArrayList从数据结构上讲是基于数组的，可以根据索引很容易的拆分为多个。
         *  推荐
         *  ArrayList
         *  HashMaps
         *  不好
         *  LinkedList
         *
         * 适用于无状态操作：每个元素的计算都不得依赖或影响任何其他元素的计算，的运算场景。
         *
         * 基础数据源无变化：从文本文件里面边读边处理的场景，不适合parallel()并行处理。parallel()一开始就容量固定的集合，这样能够平均的拆分、同步处理。
         *
         */

    }



}
