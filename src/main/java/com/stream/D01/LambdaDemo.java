package com.stream.D01;

import org.junit.Test;

/**
 * @Author: 曾睿
 * @Date: 2020/10/20 14:35
 */
public class LambdaDemo {

//01、start-----------------------------------
    /**
     * 01最初始功能，直接调用
     */
    public void printSomething(String something) {
        System.out.println(something);
    }
    @Test
    public void demo01() {
        LambdaDemo demo = new LambdaDemo();
        String something = "I am using a Functional interface";
        demo.printSomething(something);
    }
//01、end-----------------------------------

//02、start-----------------------------------
    /**
     * 没有使用lambda
     */
    //抽象功能接口
    interface Printer {
        void print(String val);
    }
    //通过参数传递功能接口
    public void printSomething(String something, Printer printer) {
        printer.print(something);
    }
    @Test
    public void demo02() {
        LambdaDemo demo = new LambdaDemo();
        String something = "I am using a Functional interface";
        //实现Printer接口
        Printer printer = new Printer() {
            @Override
            public void print(String val) {
                //控制台打印
                System.out.println(val);
            }
        };
        demo.printSomething(something, printer);
    }
//02、end-----------------------------------

//03、start-----------------------------------
    /**
     * 使用表达式
     */
    @Test
    public void demo03() {
        LambdaDemo demo = new LambdaDemo();
        String something = "I am learning Lambda";
        //实现Printer接口（请关注下面这行lambda表达式代码）
        Printer printer = (String toPrint)->{System.out.println(toPrint);};
        /*
        Printer printer = (String toPrint)->{System.out.println(toPrint);};
        //简化：去掉参数类型
        Printer printer = (toPrint)->{System.out.println(toPrint);};
        //简化：去掉参数括号
        Printer printer = toPrint->{System.out.println(toPrint);};
        //简化：去掉函数体花括号
        Printer printer = toPrint->System.out.println(toPrint);
        */
        //调用接口打印
        demo.printSomething(something, printer);
    }
//03、end-----------------------------------


//04、start-----------------------------------
    @Test
    public void demo04() {
        LambdaDemo demo = new LambdaDemo();
        String something="I am Lambda";
        //关注下面的这行代码
        demo.printSomething( something, toPrint -> System.out.println(toPrint) );
    }
//04、end-----------------------------------

}
