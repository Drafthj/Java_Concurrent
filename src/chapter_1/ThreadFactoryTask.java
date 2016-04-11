package chapter_1;

import java.util.concurrent.TimeUnit;

/**
 * Created by huojia on 2016/4/11 15:51.
 * 线程工厂类的测试
 */
public class ThreadFactoryTask implements Runnable {
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MyThreadFactory factory = new MyThreadFactory("MyThreadFactory");
        ThreadFactoryTask task = new ThreadFactoryTask();
        Thread thread;
        System.out.printf("Starting the Threads\n");
        for(int i = 0;i<10;i++){
            thread = factory.newThread(task);
            thread.start();
        }
        System.out.println("Factory stats:");
        System.out.println(factory.getStats());
    }
}
