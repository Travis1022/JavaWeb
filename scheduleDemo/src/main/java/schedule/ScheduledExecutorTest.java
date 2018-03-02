package schedule;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 任务调度方式之二：使用ScheduledExecutor
 * <p>
 * 设计思想是，每一个被调度的任务都会由线程池中一个线程去执行，因此任务是并发执行的，相互之间不会受到干扰。需要注意的是，只有当任务的执行时间到来时，
 * ScheduledExecutor 才会真正启动一个线程，其余时间 ScheduledExecutor 都是在轮询任务的状态。
 * <p>
 * Created by txw on 2018/2/26.
 */
public class ScheduledExecutorTest implements Runnable {

    private String jobName;


    public ScheduledExecutorTest(String jobName) {
        this.jobName = jobName;
    }

    public void run() {
        System.out.println(jobName);
    }


    public static void main(String[] args) {
        int initDelay1 = 2;
        int initDelay2 = 4;
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
        int period1 = 1;
        int period2 = 2;

        //ScheduleAtFixedRate 是基于固定时间间隔进行任务调度
        //执行时间为initialDelay+period, initialDelay+2*period, …；
        //ScheduleWithFixedDelay 取决于每次任务执行的时间长短，是基于不固定时间间隔进行任务调度
        //执行时间为initialDelay, initialDelay+executeTime+delay, initialDelay+2*executeTime+2*delay
        service.scheduleAtFixedRate(new ScheduledExecutorTest("job1"), initDelay1, period1, TimeUnit.SECONDS);
        service.scheduleWithFixedDelay(new ScheduledExecutorTest("job2"), initDelay2, period2, TimeUnit.SECONDS);
    }
}
