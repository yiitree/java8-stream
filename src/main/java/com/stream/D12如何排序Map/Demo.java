package com.stream.D12如何排序Map;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @Author: 曾睿
 * @Date: 2020/10/29 10:21
 */
public class Demo {

    @Test
    public void Test01() {
        //HashMap的merge()函数
//        参数一：向map里面put的键
//        参数二：向map里面put的值
//        参数三：如果键发生重复，如何处理值。可以是一个函数，也可以写成lambda表达式。
        String k = "key";
        HashMap<String, Integer> map = new HashMap<String, Integer>() {{
            put(k, 1);
        }};
        //返回旧值oldVal加上新值newVal(1+2)，现在map里面只有一项元素那就是k:3。
        map.merge(k, 2, (oldVal, newVal) -> oldVal + newVal);
    }


    @Test
    public void Test02() {
        // 创建一个Map，并填入数据
        Map<String, Integer> codes = new HashMap<>();
        codes.put("United States", 1);
        codes.put("Germany", 49);
        codes.put("France", 33);
        codes.put("China", 86);
        codes.put("Pakistan", 92);

        // 按照Map的键进行排序
        Map<String, Integer> sortedMap = codes.entrySet().stream()
            //排序的依据是Map.Entry.comparingByKey()，也就是按照Map的键排序
            .sorted(Map.Entry.comparingByKey())
            .collect(
                Collectors.toMap(
                    Map.Entry::getKey,
                    Map.Entry::getValue,
                    (oldVal, newVal) -> oldVal,
                    //Stream流转成LinkedHashMap
                    LinkedHashMap::new
                )
            );
        // 将排序后的Map打印
        sortedMap.entrySet().forEach(System.out::println);
        //LinkedHashMap来存储排序的结果以保持顺序。默认情况下，Collectors.toMap()返回HashMap。HashMap不能保证元素的顺序。
        //China=86
        //France=33
        //Germany=49
        //Pakistan=92
        //United States=1

        //四、按Map的值排序
        Map<String, Integer> sortedMap2 = codes.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldVal, newVal) -> oldVal,
                        LinkedHashMap::new));

        sortedMap2.entrySet().forEach(System.out::println);
        //United States=1
        //France=33
        //Germany=49
        //China=86
        //Pakistan=92

        //五、使用TreeMap按键排序
        // 将 `HashMap` 转为 `TreeMap`
        Map<String, Integer> sorted = new TreeMap<>(codes);
        //China=86
        //France=33
        //Germany=49
        //Pakistan=92
        //United States=1

    }
}
