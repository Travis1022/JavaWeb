package designPattern.single;

/**
 * 静态内部类单例模式
 * 线程安全
 * Created by txw on 2018/2/28.
 */
public class Singleton {

      /*单例模式使用场景：在一个系统中，要求有一个类有且仅有一个对象
          1.整个项目需要一个共享访问点或共享数据
          2.创建一个对象需要耗费的资源过多，比如访问IO或者数据库等资源
          3.工具类对象
        */

    public Singleton() {

    }

    public static Singleton getInstance() {
        return SingletonHolder.sInstance;
    }


    private static class SingletonHolder {

        private static final Singleton sInstance = new Singleton();
    }


}
