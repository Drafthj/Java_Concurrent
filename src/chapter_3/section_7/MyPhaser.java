package chapter_3.section_7;

import java.util.concurrent.Phaser;

/**
 * Created by huojia on 2016/5/5 15:29.
 * 当Phaser阶段改变时，会调用onAdvance()方法，重写
 * onAdvance()方法可以在阶段改变时做一些额外的操作。
 */
public class MyPhaser extends Phaser{
    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        return super.onAdvance(phase, registeredParties);
    }
    private boolean studentsArrived(){
        System.out.printf("Phaser: The exam are going to start.The students are ready.\n");
        System.out.printf("Phaser: We have %d students.\n",getRegisteredParties());
        return false;
    }
    private boolean finishFirstExercise(){
        System.out.printf("Phaser: All the students have finished the first exercise.\n");
        System.out.printf("Phaser: it's time for the second one.\n");
        return false;
    }
    private boolean finishSecondExercise(){
        System.out.printf("Phaser: All the student have finished the second exercise.\n");
        System.out.printf("Phaser: it's time for the third one");
        return false;
    }
    private boolean finishExam(){
        System.out.printf("Phaser: All the student have finished the exam.\n");
        System.out.printf("Phaser: Thank you for your time.");
        return true;
    }
}
