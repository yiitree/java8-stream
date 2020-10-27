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
 * @Author: 曾睿
 * @Date: 2020/10/20 17:13
 */
public class Demo1 {

    @Test
    public void demo1(){
        List<String> nameStrs = Arrays.asList("Monkey", "Lion", "Giraffe","Lemur");

        List<String> list = nameStrs.stream()
                .filter(s -> s.startsWith("L"))
                .map(String::toUpperCase)
                .sorted()
                .collect(toList());
        System.out.println(list);
    }

    @Test
    public void demo2(){
        String[] array = {"Monkey", "Lion", "Giraffe", "Lemur"};
        Stream<String> nameStrs2 = Stream.of(array);
        Stream<String> nameStrs3 = Stream.of("Monkey", "Lion", "Giraffe", "Lemur");
    }

    @Test
    public void demo3(){
        //------------
        List<String> list = Arrays.asList("Monkey", "Lion", "Giraffe", "Lemur");
        Stream<String> streamFromList = list.stream();

        Set<String> set = new HashSet<>(list);
        Stream<String> streamFromSet = set.stream();
    }

    @Test
    public void demo4() throws IOException {
        Stream<String> lines = Files.lines(Paths.get("file.txt"));
    }

}
