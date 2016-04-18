package chapter_2.section_7;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by huojia on 2016/4/18 15:47.
 * 通过构造参数修改锁的公平性
 */
public class PrintQueue {
    private final Lock queueLock = new ReentrantLock(false);

    public void printJob(Object document){
        queueLock.lock();
        try {
            Long duration = (long)(Math.random()*10000);
            System.out.println(Thread.currentThread().getName()+ ":PrintQueue: Printing a Job during "
                    + (duration/1000)+" seconds");
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            queueLock.unlock();
        }
    }
}
