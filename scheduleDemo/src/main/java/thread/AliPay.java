package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁与条件对象
 * <p>
 * Created by txw on 2018/2/27.
 */
public class AliPay {

    private Lock alipayLock;

    private Condition condition;


    private double[] accounts;

    public AliPay(int n, double money) {
        accounts = new double[n];
        alipayLock = new ReentrantLock();
        //得到条件对象
        condition = alipayLock.newCondition();
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = money;
        }
    }


    /**
     * 转账操作
     *
     * @param from   转账方
     * @param to     接收方
     * @param amount 转账金额
     * @throws InterruptedException
     */
    public void transfer(int from, int to, int amount) throws InterruptedException {

        try {
            alipayLock.lock();
            while (accounts[from] < amount) {
                //阻塞当前线程，并且放弃锁
                condition.await();
                System.out.println("转账失败");
            }
            //转账操作
            accounts[from] = accounts[from] - amount;
            accounts[to] = accounts[to] + amount;
            condition.signalAll();
            System.out.println("转账成功");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            alipayLock.unlock();
        }

    }

    /**
     * 转账操作
     * <p>
     * 同步方法
     *
     * @param from
     * @param to
     * @param amount
     * @throws InterruptedException
     */
    public synchronized void transfer2(int from, int to, int amount) throws InterruptedException {
        while (accounts[from] < amount) {
            wait();
            System.out.println("转账失败");

        }
        //转账操作
        accounts[from] = accounts[from] - amount;
        accounts[to] = accounts[to] + amount;
        notifyAll();
        System.out.println("转账成功");

    }


}
