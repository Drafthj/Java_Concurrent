package chapter_4.section_7;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by huojia on 2016/5/9 10:16.
 * 延迟执行同步任务
 *
 * ScheduledThreadPoolExecutor执行器可以指定
 * 线程延迟一定时间执行，可以用来执行定时任务
 */
public class Main {
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
        System.out.printf("Main: Starting at: %s\n",new Date());
        for(int i = 0;i<5;i++){
            Task task = new Task("Task "+i);
            executor.schedule(task,i+1, TimeUnit.SECONDS);
        }
        executor.shutdown();
        try {
            executor.awaitTermination(1,TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Main: End at: %s\n",new Date());
    }
}
