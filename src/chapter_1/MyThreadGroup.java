package chapter_1;

/**
 * Created by huojia on 2016/4/11 11:27.
 * 重写线程组中的uncaughtException()方法，来捕获线程组中线程的非捕获异常
 */
public class MyThreadGroup extends ThreadGroup{
    public MyThreadGroup(String name) {
        super(name);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.printf("The thread %s has thrown an Exception\n",t.getId());
        e.printStackTrace(System.out);
        System.out.printf("Terminating the rest of the Threads\n");
        interrupt();
    }
}
