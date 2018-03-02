package designPattern.factory;

/**
 * Created by txw on 2018/3/1.
 */
public class ComputerFactory {

    /*简单工厂模式
    * 使用场景：
    * 1.工厂类负责创建的对象比较少
    * 2.客户只需知道传入工厂类的参数，而无需关心创建对象的逻辑
    *
    * 避免了直接实例化类，降低了耦合性
    *
    * 简单工厂需要知道所有要生成的类型，当子类过多时不适合使用
    * 违背了开放封闭原则。
    *
    * */


    public static Computer createComputer(String type) {
        Computer mComputer = null;

        switch (type) {
            case "lenovo":
                mComputer = new LenoveComputer();
                break;
            case "hp":
                mComputer = new HpComputer();
                break;
            case "asus":
                mComputer = new AsusComputer();
                break;
        }

        return mComputer;
    }
}
