package com.stream.D08函数式接口;

import com.stream.D03filter与谓语逻辑.Employee;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: 曾睿
 * @Date: 2020/10/28 16:30
 */
public class Demo {

    public void demo(){

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

        /*
         * 所谓的函数式接口，实际上就是接口里面只能有一个抽象方法的接口。这样就可以改写为lambda表达式了
         *
         * 接口知识：
         * java8之前，接口不能有具体方法，要是实现接口就必须实现所有方法
         * java8之后，接口可以有默认default方法，实现接口可以不用重写改方法
         */

        /*
        Comparator接口:
        两个抽象方法
            int compare(T o1, T o2); ---唯一一个没有实现的接口
            boolean equals(Object obj); ---为object中public方法，所以其实为默认有实现的，所以这个需要排除
        剩下的为default方法
            ....

        所谓的函数式接口，实际上就是接口里面只能有一个抽象方法的接口。

        接口有且仅有一个抽象方法，如上图的抽象方法compare
        允许定义静态非抽象方法
        允许定义默认defalut非抽象方法（default方法也是java8才有的，见下文）
        允许java.lang.Object中的public方法，如上图的方法equals。
        FunctionInterface注解不是必须的，如果一个接口符合"函数式接口"定义，那么加不加该注解都没有影响。加上该注解能够更好地让编译器进行检查。如果编写的不是函数式接口，但是加上了@FunctionInterface，那么编译器会报错
        甚至可以说：函数式接口是专门为lambda表达式准备的，lambda表达式是只实现接口中唯一的抽象方法的匿名实现类。
         */

        employees.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee em1, Employee em2) {
                if(em1.getAge().equals(em2.getAge())){
                    return 0;
                }
                return em1.getAge() - em2.getAge() > 0 ? -1:1;
            }
        });
        employees.forEach(System.out::println);

        // 使用lambda
        employees.sort((em1,em2) -> {
            if(em1.getAge().equals(em2.getAge())){
                return 0;
            }
            return em1.getAge() - em2.getAge() > 0 ? -1:1;
        });
        employees.forEach(System.out::println);



    }



}
