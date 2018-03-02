package thread;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 使用阻塞队列实现的生产者消费者的问题
 * <p>
 * 使用阻塞队列无需考虑同步以及线程间通信的问题
 * Created by txw on 2018/2/27.
 */
public class BlockQueueTest {

    private int queueSize = 10;

    private ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(queueSize);


    public static void main(String[] args) {

        BlockQueueTest blockQueueTest = new BlockQueueTest();
        Producer producer = blockQueueTest.new Producer();
        Consumer consumer = blockQueueTest.new Consumer();

        Thread produceThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        produceThread.start();
        consumerThread.start();

    }


    /**
     * 消费者
     */
    class Consumer implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    queue.take();
                    System.out.println("取走一个");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 生产者
     */
    class Producer implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    queue.put(1);
                    System.out.println("生产一个");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}





