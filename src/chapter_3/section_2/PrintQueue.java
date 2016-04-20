package chapter_3.section_2;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by huojia on 2016/4/19 14:13.
 * 二进制信号量的实现
 */
public class PrintQueue {
    private final Semaphore semaphore;

    public PrintQueue() {
        this.semaphore = new Semaphore(1);
    }

    public void printJob(Object document){
        try {
            semaphore.acquire();
            long duration = (long) (Math.random()*10);
            System.out.printf("%s: PrintQueue:Printing a Job during %d seconds\n",Thread.currentThread().getName(),duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
    }
}
