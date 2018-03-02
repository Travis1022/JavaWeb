package schedule;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 任务调度方式之一：使用Timer
 * <p>
 * 1.使用 Timer 实现任务调度的核心类是 Timer 和 TimerTask。其中 Timer 负责设定 TimerTask 的起始与间隔执行时间。使用者只需要创建一个 TimerTask 的继承类，实现自己的 run 方法，然后将其丢给 Timer 去执行即可。
 * 2.Timer 的设计核心是一个 TaskList 和一个 TaskThread。Timer 将接收到的任务丢到自己的 TaskList 中，TaskList 按照 Task 的最初执行时间进行排序。TimerThread 在创建 Timer 时会启动成为一个守护线程。这个线程会轮询所有任务，找到一个最近要执行的任务，然后休眠，当到达最近要执行任务的开始时间点，TimerThread 被唤醒并执行该任务。之后 TimerThread 更新最近一个要执行的任务，继续休眠。
 * 3.Timer 的优点在于简单易用，但由于所有任务都是由同一个线程来调度，因此所有任务都是串行执行的，同一时间只能有一个任务在执行，前一个任务的延迟或异常都将会影响到之后的任务。
 * Created by txw on 2018/2/26.
 */
public class TimerTest extends TimerTask {

    private String jobName;


    public TimerTest(String jobName) {
        this.jobName = jobName;
    }

    @Override
    public void run() {
        System.out.println(jobName);
    }

    public static void main(String[] args) {


        Timer timer = new Timer();
        int delay1 = 1 * 1000;
        int period = 1000;

        timer.schedule(new TimerTest("job1"), delay1, period);

        int delay2 = 2 * 1000;
        int period2 = 2000;
        timer.schedule(new TimerTest("job2"), delay2, period2);
    }
}
