package com.stream.D10集合元素归约;

import com.stream.D03filter与谓语逻辑.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 其实就是：把list中的遍历元素，求个值
 * 其实就是把集合内的元素转化为对象
 */
public class Demo {

    //nteger类型归约
    @Test
    public void Test(){
        /**
         * Identity标识：一个元素，它是归约操作的初始值，如果流为空，则为默认结果。
         * Accumulator累加器：具有两个参数的函数：归约运算的部分结果和流的下一个元素。
         * Combiner合并器（可选）：当归约并行化时，或当累加器参数的类型与累加器实现的类型不匹配时，用于合并归约操作的部分结果的函数。
         */
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int result = numbers.stream()
                // 初始值 阶段累加结果 遍历的元素
                .reduce(0, (subtotal, element) -> subtotal + element);
        System.out.println(result);  //21

        int result1 = numbers.stream()
                .reduce(0, Integer::sum);
        System.out.println(result); //21
    }

    //String类型归约
    @Test
    public void Test01() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int result = numbers.stream()
                .reduce(0, (subtotal, element) -> subtotal + element);
        System.out.println(result);  //21
        int result1 = numbers.stream()
                .reduce(0, Integer::sum);
        System.out.println(result); //21
    }

    //复杂对象归约
    @Test
    public void Test02() {
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

        Integer total = employees.stream().map(Employee::getAge).reduce(0,Integer::sum);
        //346
        System.out.println(total);

        // 并行计算（推荐）速度快
        // 初始值 累加器 合并器
        // 合并器作用：并行运算其实就是把list拆开成几个，累加器计算每一组的结果，然后使用合并器把每组结果进行合并（此时也会进行二次规约，就是类型转换）
        Integer reduce = employees.parallelStream().map(Employee::getAge).reduce(0, Integer::sum, Integer::sum);

        Integer total3 = employees.stream()
                .reduce(0,(totalAge,emp) -> totalAge + emp.getAge(),Integer::sum); //注意这里reduce方法有三个参数


    }
}
