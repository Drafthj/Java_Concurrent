package chapter_3.section_7;

import java.util.Date;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * Created by huojia on 2016/5/5 16:22.
 */
public class Student implements Runnable{
    private Phaser phaser;

    public Student(Phaser phaser) {
        this.phaser = phaser;
    }

    @Override
    public void run() {
        System.out.printf("%s: Has arrived to the exam.%s\n",Thread.currentThread().getName(),new Date());
        phaser.arriveAndAwaitAdvance();
        System.out.printf("%s: Is going to do the first exercise.%s\n",Thread.currentThread().getName(),new Date());
        doExercis1();
        System.out.printf("%s :Has done the first exercise.%s\n",Thread.currentThread().getName(),new Date());
        phaser.arriveAndAwaitAdvance();
        System.out.printf("%s: Is going to do the second exercise.%s\n",Thread.currentThread().getName(),new Date());
        doExercis2();
        System.out.printf("%s :Has done the second exercise.%s\n",Thread.currentThread().getName(),new Date());
        phaser.arriveAndAwaitAdvance();
        System.out.printf("%s: Is going to do the third exercise.%s\n",Thread.currentThread().getName(),new Date());
        doExercis3();
        System.out.printf("%s :Has done the third exercise.%s\n",Thread.currentThread().getName(),new Date());
        phaser.arriveAndAwaitAdvance();
    }

    /**
     * 模拟做题
     */
    private void doExercis1(){
        long duration = (long) (Math.random()*10);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void doExercis2(){
        long duration = (long) (Math.random()*10);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void doExercis3(){
        long duration = (long) (Math.random()*10);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
