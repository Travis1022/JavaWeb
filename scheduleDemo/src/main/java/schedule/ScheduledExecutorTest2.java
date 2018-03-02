package schedule;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 任务调度方式之三：用 ScheduledExecutor 和 Calendar 实现复杂任务调度
 * <p>
 * Created by txw on 2018/2/26.
 */
public class ScheduledExecutorTest2 implements Runnable {

    private String jobName;


    public ScheduledExecutorTest2(String jobName) {
        this.jobName = jobName;
    }

    public void run() {
        System.out.println("Date : " + new Date());
        System.out.println("executor : " + jobName);

    }

    /**
     * 查询满足条件的当前最近的时间
     *
     * @param currentDate
     * @param dayOfWeek
     * @param hourOfDay
     * @param minuteOfHour
     * @param secondOfMinute
     * @return
     */
    private Calendar getEarliestDate(Calendar currentDate, int dayOfWeek,
                                     int hourOfDay, int minuteOfHour, int secondOfMinute) {
        int currentWeekOfYear = currentDate.get(Calendar.WEEK_OF_YEAR);
        int currentDayOfWeek = currentDate.get(Calendar.DAY_OF_WEEK);
        int currentHourOfDay = currentDate.get(Calendar.HOUR_OF_DAY);
        int currentMinute = currentDate.get(Calendar.MINUTE);
        int currentSecond = currentDate.get(Calendar.SECOND);

        boolean weekLater = false;
        if (dayOfWeek < currentDayOfWeek) {
            weekLater = true;
        } else if (dayOfWeek == currentDayOfWeek) {
            if (hourOfDay < currentHourOfDay) {
                weekLater = true;
            } else if (hourOfDay == currentHourOfDay) {
                if (minuteOfHour < currentMinute) {
                    weekLater = true;
                } else if (minuteOfHour == currentMinute) {
                    if (secondOfMinute < currentSecond) {
                        weekLater = true;
                    }
                }
            }
        } else {
            //
        }
        if (weekLater) {
            currentDate.set(Calendar.WEEK_OF_YEAR, currentWeekOfYear + 1);
        }
        currentDate.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        currentDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
        currentDate.set(Calendar.MINUTE, minuteOfHour);
        currentDate.set(Calendar.SECOND, secondOfMinute);

        return currentDate;
    }

    /**
     * 每星期三 20:50:10 调度任务
     *
     * @param args
     */
    public static void main(String[] args) {
        ScheduledExecutorTest2 test2 = new ScheduledExecutorTest2("job1");
        //
        Calendar currentDate = Calendar.getInstance();
        long currentDateLong = currentDate.getTime().getTime();
        System.out.println("Current Date : " + currentDate.getTime().toString());

        Calendar earliestDate = test2.getEarliestDate(currentDate, 4, 20, 50, 10);

        long earliestDateLong = earliestDate.getTime().getTime();
        System.out.println("Earliest Date : " + earliestDate.getTime().toString());

        long delay = earliestDateLong - currentDateLong;

        long period = 7 * 24 * 60 * 60 * 1000;

        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);

        service.scheduleAtFixedRate(test2, delay, period, TimeUnit.MILLISECONDS);
    }


}
