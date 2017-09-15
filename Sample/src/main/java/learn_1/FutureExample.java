package learn_1;

import java.util.concurrent.*;

/**
 * 二、使用Future实现异步
 * Created by txw on 2017/9/13.
 */
public class FutureExample {

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Runnable task1 = new Runnable() {
            public void run() {
                // do something
                System.out.println("task1......");
            }
        };
        Callable<Integer> task2 = new Callable<Integer>() {
            public Integer call() throws Exception {
                //do something
                return new Integer(100);
            }
        };

        Future<?> future = executorService.submit(task1);
        Future<Integer> future1 = executorService.submit(task2);

        System.out.println("task1 is completed ? " + future.isDone());
        System.out.println("task2 is completed ? " + future1.isDone());

        //wait
        while (future.isDone()) {
            System.out.println("task1 is completed.");
            break;
        }

        //wait
        while (future1.isDone()) {
            System.out.println("task2 is completed.");
            System.out.println("return value by task2 :" + future1.get());
            break;
        }
    }
}
