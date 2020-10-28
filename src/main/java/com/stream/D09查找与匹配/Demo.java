package com.stream.D09查找与匹配;

import com.stream.D03filter与谓语逻辑.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @Author: 曾睿
 * @Date: 2020/10/28 16:44
 */
public class Demo {
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

    @Test
    public void test(){
        //查找员工列表中是否包含年龄大于70的员工
        boolean isExistAgeThan70 = false;
        for(Employee employee:employees){
            if(employee.getAge() > 70){
                isExistAgeThan70 = true;
                break;
            }
        }

        // 匹配规则函数：anyMatch
        // 判断Stream流中是否包含某一个“匹配规则”的元素。
        // 这个匹配规则可以是lambda表达式或者谓词。
        boolean isExistAgeThan700 = employees.stream().anyMatch(Employee.ageGreaterThan70);
        boolean isExistAgeThan72 = employees.stream().anyMatch(e -> e.getAge() > 72);

        //是否所有员工的年龄都大于10岁
        //allMatch匹配规则函数：判断是够Stream流中的所有元素都符合某一个"匹配规则"。
        boolean isExistAgeThan10 = employees.stream().allMatch(e -> e.getAge() > 10);

        //是否不存在小于18岁的员工
        //noneMatch匹配规则函数：判断是否Stream流中的所有元素都不符合某一个"匹配规则"。
        boolean isExistAgeLess18 = employees.stream().noneMatch(e -> e.getAge() < 18);


        // 元素查找与Optional
        // 从列表中按照顺序查找第一个年龄大于40的员工。
        // findFirst用于查找第一个符合“匹配规则”的元素，返回值为Optional
        // findAny  用于查找任意一个符合“匹配规则”的元素，返回值为Optional
        // Employee(id=3, age=43, gender=M, firstName=Ricky, lastName=Martin)
        Optional<Employee> employeeOptional = employees.stream().filter(e -> e.getAge() > 40).findFirst();

        // get() 会在值存在时返回值，否则?出一个 NoSuchElement 异常。
        Employee employee1 = employeeOptional.get();

        // isPresent() 将在 Optional 包含值的时候返回 true , 否则返回 false 。
        boolean present = employeeOptional.isPresent();


        // ifPresent(Consumer block) 会在值存在的时候执行给定的代码块。
        //我们在第3章介绍了 Consumer 函数式接口；它让你传递一个接收 T 类型参数，并返回 void 的Lambda表达式。
        employeeOptional.ifPresent(
                //可以传递一个表达式进行操作
                System.out::println
        );

        // orElse(T other) 会在值存在时返回值，否则返回一个默认值。
        Employee employee = employeeOptional.orElse(
                new Employee(10, 45, "M", "Naveen", "Jain")
        );

    }

}
