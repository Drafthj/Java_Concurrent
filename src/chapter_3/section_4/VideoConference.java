package chapter_3.section_4;

import java.util.concurrent.CountDownLatch;

/**
 * Created by huojia on 2016/5/3 15:09.
 * 用线程同步辅助类CountDownLatch来实现等待多个并发事件的完成
 *
 */
public class VideoConference implements Runnable{
    private final CountDownLatch controller;

    public VideoConference(int number) {
     controller = new CountDownLatch(number);
    }
    public void arrive(String name){
        System.out.printf("%s has arrived.\n",name);
        controller.countDown();
        System.out.printf("VideoConference: Waiting for %d participants.\n",controller.getCount());
    }
    @Override
    public void run() {
        System.out.printf("VideoConference: Initialization: %d participants.\n",controller.getCount());
        try {
            controller.await();
            System.out.printf("VideoConference: All the participants have come\n");
            System.out.println("Let's start.....");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
