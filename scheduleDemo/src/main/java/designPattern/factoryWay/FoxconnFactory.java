package designPattern.factoryWay;

import designPattern.factory.Computer;

/**
 * Created by Administrator on 2018/3/1.
 */
public class FoxconnFactory extends ComFactory {
    /*
    工厂方法模式类似于简单工厂模式，但是没有违背开闭原则
    如果有新增产品，直接创建产品即可，无需修改工厂类
    */


    @Override
    public <T extends Computer> T createComputer(Class<T> clz) {
        Computer computer = null;
        String className = clz.getName();

        try {
            computer = (Computer) Class.forName(className).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return (T) computer;
    }
}
