package designPattern.factoryWay;

import designPattern.factory.Computer;

/**
 * Created by Administrator on 2018/3/1.
 */
public abstract class ComFactory {
    public abstract <T extends Computer> T createComputer(Class<T> clz);
}
