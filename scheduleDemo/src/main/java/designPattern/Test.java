package designPattern;

import designPattern.builder.Builder;
import designPattern.builder.Director;
import designPattern.builder.Merchant;
import designPattern.factory.ComputerFactory;
import designPattern.factory.HpComputer;
import designPattern.factory.LenoveComputer;
import designPattern.factoryWay.ComFactory;
import designPattern.factoryWay.FoxconnFactory;
import designPattern.single.Singleton;
import designPattern.single.Student;

/**
 * Created by Administrator on 2018/3/1.
 */
public class Test {


    public static void main(String[] args) {

        /**
         * 单例模式
         */
        Singleton singleton = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        Singleton singleton3 = Singleton.getInstance();

        System.out.println(singleton.toString());
        System.out.println(singleton2.toString());
        System.out.println(singleton3.toString());

        Student student = new Student();
        Student student2 = new Student();
        Student student3 = new Student();

        System.out.println(student.toString());
        System.out.println(student2.toString());
        System.out.println(student3.toString());

        /**
         * 简单工厂模式
         */
        ComputerFactory.createComputer("hp").start();


        /**
         * 工厂方法模式
         */
        ComFactory foxconnFactory = new FoxconnFactory();
        LenoveComputer lenoveComputer = foxconnFactory.createComputer(LenoveComputer.class);
        lenoveComputer.start();

        HpComputer hpComputer = foxconnFactory.createComputer(HpComputer.class);
        hpComputer.start();


        /**
         * 建造者模式
         */
        Builder builder = new Merchant();
        Director director = new Director(builder);
        director.createMainFrame("i5-6500", "AMD", "三星DDR4");


    }
}
