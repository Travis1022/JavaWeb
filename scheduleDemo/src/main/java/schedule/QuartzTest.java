package schedule;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 任务调度方式之四：使用Quartz
 * <p>
 * Quartz 设计的核心类包括 Scheduler, Job 以及 Trigger。
 * Job 负责定义需要执行的任务，
 * Trigger 负责设置调度策略，
 * Scheduler 将二者组装在一起，并触发任务开始执行。
 * <p>
 * Created by Administrator on 2018/2/27.
 */
public class QuartzTest implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

    }

    public static void main(String[] args) {

        try {
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.start();


            JobDetail jobDetail = JobBuilder.newJob(QuartzTest.class).withIdentity("myJob", "myJobGroup").build();

            jobDetail.getJobDataMap().put("type", "FULL");

            //TriggerUtils.computeEndTimeToAllowParticularNumberOfFirings();


        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
