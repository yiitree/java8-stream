package com.stream.D13Stream流逐行文件处理;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: 曾睿
 * @Date: 2020/10/29 10:52
 */
public class Demo {

    @Test
    public void Test01() {
        Path filePath = Paths.get("c:/temp", "data.txt");

        //try-with-resources语法,不用手动的编码关闭流
        try (Stream<String> lines = Files.lines( filePath )) {
            lines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();//只是测试用例，生产环境下不要这样做异常处理
        }
        //Never
        //store
        //password
        //except
        //in mind.

    }

    //过滤行
    @Test
    public void Test02() {
        Path filePath = Paths.get("c:/temp", "data.txt");
        //过滤其中包含单词"password"的所有行
        try (Stream<String> lines = Files.lines(filePath)){
            List<String> filteredLines = lines
                    .filter(s -> s.contains("password"))
                    .collect(Collectors.toList());
            filteredLines.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();//只是测试用例，生产环境下不要这样做异常处理
        }
        //password
    }
}
