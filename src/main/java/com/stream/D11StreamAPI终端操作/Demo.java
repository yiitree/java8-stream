package com.stream.D11StreamAPI终端操作;

import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.toList;

/**
 * 其实就是管道流走完之后，按照什么返回，转化为list、array、map等等
 */
public class Demo {


    //一、Java Stream管道数据处理操作
    @Test
    public void Test01() {

        Stream<String> monkey = Stream.of("Monkey", "Lion", "Giraffe", "Lemur", "Lion");
        List<String> nameStrs = Arrays.asList("Monkey", "Lion", "Giraffe", "Lemur");
        Stream<String> stream = nameStrs.stream();


        List<String> list = nameStrs.stream()
                .filter(s -> s.startsWith("L"))
                .map(String::toUpperCase)
                .sorted()
                .collect(toList());
        System.out.println(list);

        List<String> lists = nameStrs.stream()
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) {
                        return s.startsWith("L");
                    }
                })
                .collect(toList());
        System.out.println(list);
    }


    //二、ForEach和ForEachOrdered
    //steam.forEach()和list.forEach()不一样哈
    @Test
    public void Test02() {
        // 顺序不一致 (steam.forEach()和list.forEach()不一样哈)
        //如果我们只是希望将Stream管道流的处理结果打印出来，而不是进行类型转换，我们就可以使用forEach()方法或forEachOrdered()方法。
        Stream.of("Monkey", "Lion", "Giraffe", "Lemur", "Lion").parallel().forEach(System.out::println);

        // 顺序一致
        // forEach从名字上看就可以理解，虽然在数据处理顺序上可能无法保障，
        // 但是forEachOrdered方法可以在元素输出的顺序上保证与元素进入管道流的顺序一致。
        // 也就是下面的样子（forEach方法则无法保证这个顺序）：
        Stream.of("Monkey", "Lion", "Giraffe", "Lemur", "Lion").parallel().forEachOrdered(System.out::println);
    }

    // 三、元素的收集collect
    @Test
    public void Test03() {

        Stream<String> monkey = Stream.of("Monkey", "Lion", "Giraffe", "Lemur", "Lion");

        // 3.1.收集为Set  注意Set会去重
        Set<String> collectToSet = monkey.collect(Collectors.toSet());

        // 3.2.收集到List
        List<String> collectToList = monkey.collect(Collectors.toList());

        //3.3.通用的收集方式（使用构造方法） 转换为LinkedList、LinkedHashSet::new、PriorityQueue::new 等等
        LinkedList<String> collectToCollection = monkey.collect(Collectors.toCollection(LinkedList::new));

        //3.4.收集到Array
        String[] toArray = monkey.toArray(String[]::new);

        //3.5.收集到Map
        //使用Collectors.toMap()方法将数据元素收集到Map里面，但是出现一个问题：
        // 那就是管道中的元素是作为key，还是作为value。
        // 我们用到了一个Function.identity()方法，该方法很简单就是返回一个“ t -> t ”（输入就是输出的lambda表达式）。
        // 另外使用管道流处理函数distinct()来确保Map键值的唯一性。
        Map<String, Integer> toMap = monkey
                .distinct()
                .collect(Collectors.toMap(
                            //元素输入就是输出，作为key
                            Function.identity(),
                            // 输入元素的不同的字母个数，作为value
                            s -> (int) s.chars().distinct().count()
                ));
        // 最终toMap的结果是: {Monkey=6, Lion=4, Lemur=5, Giraffe=6}

        //3.6.分组收集groupingBy
        // Collectors.groupingBy用来实现元素的分组收集，下面的代码演示如何根据首字母将不同的数据元素收集到不同的List，并封装为Map。
        Map<Character, List<String>> groupingByList = monkey
                .collect(Collectors.groupingBy(
                        // 分组条件: 根据元素首字母分组，相同的在一组
                        s -> s.charAt(0)
                ));
        // 最终groupingByList内的元素: {G=[Giraffe], L=[Lion, Lemur, Lion], M=[Monkey]}

        Map<Object, Long> groupingByLists =  monkey
                .collect(Collectors.groupingBy(
                        // 分组条件: 根据元素首字母分组，相同的在一组
                        s -> s.charAt(0),
                        // 子收集器: 加上这一行代码可以实现分组统计
                        counting()
                ));
        // 如果加上counting() ，结果是:  {G=1, L=3, M=1}

        //其他常用方法
        // 1.判断管道中是否包含2，结果是: true
        boolean containsTwo = IntStream.of(1, 2, 3).anyMatch(i -> i == 2);

        // 2.管道中元素数据总计结果nrOfAnimals: 4
        long nrOfAnimals = monkey.count();

        // 3.管道中元素数据累加结果sum: 6
        int sum = IntStream.of(1, 2, 3).sum();

        // 4.管道中元素数据平均值average: OptionalDouble[2.0]
        OptionalDouble average = IntStream.of(1, 2, 3).average();

        // 5.管道中元素数据最大值max: 3
        int max = IntStream.of(1, 2, 3).max().orElse(0);

        // 6.全面的统计结果statistics: IntSummaryStatistics{count=3, sum=6, min=1, average=2.000000, max=3}
        IntSummaryStatistics statistics = IntStream.of(1, 2, 3).summaryStatistics();

    }



}
