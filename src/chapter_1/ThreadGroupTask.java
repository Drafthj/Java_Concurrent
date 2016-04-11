package chapter_1;

import java.util.Random;

/**
 * Created by huojia on 2016/4/11 11:30.
 * 测试线程组的非捕获异常处理器
 */
public class ThreadGroupTask implements Runnable {
    @Override
    public void run() {
        int result;
        Random random = new Random(Thread.currentThread().getId());
        while (true){
            result = 1000/((int)(random.nextDouble()*1000));
            System.out.printf("%s : %d\n",Thread.currentThread().getId(),result);
            if(Thread.currentThread().isInterrupted()) {
                System.out.printf("%d : Interrupted\n", Thread.currentThread().getId());
                return;
            }
        }
    }

    public static void main(String[] args) {
        MyThreadGroup threadGroup = new MyThreadGroup("MyThreadGroup");
        ThreadGroupTask task = new ThreadGroupTask();
        for (int i =0;i<2;i++){
            Thread t = new Thread(threadGroup,task);
            t.start();
        }
    }
}
