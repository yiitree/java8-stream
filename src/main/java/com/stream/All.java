package com.stream;

import com.stream.D03filter与谓语逻辑.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: 曾睿
 * @Date: 2020/10/29 11:33
 */
public class All {

    public static void main(String[] args) {

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


        List<Employee> filtered02 = employees
                //转换为管道流
                .stream()
                //并行处理
                .parallel()
                //函数过滤筛选 and or negate
                // and()
                .filter(e -> e.getAge() > 70 && e.getGender().equals("M"))
                //对管道流中每个元素进行处理 map、peek
                .map(e -> {
                    e.setAge(e.getAge() + 1);
                    e.setGender(e.getGender().equals("M")?"male":"female");
                    return e;
                })
                .peek(e -> {
                    e.setAge(e.getAge() + 1);
                    e.setGender(e.getGender().equals("M")?"male":"female");
                })
                //截取管道中的前n个元素
                .limit(2)
                //跳过前n个元素，截取从n到末尾的元素
                .skip(2)
                //去重，equals方法进行对象的比较的
                .distinct()
                //然后调用sort函数，对管道流中数据进行排序
                .sorted()
                //最后调用collect函数toList，将管道流转换为List返回
                .collect(Collectors.toList());
    }

}
