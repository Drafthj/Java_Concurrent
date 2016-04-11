package chapter_1;

/**
 * Created by huojia on 2016/4/8 10:29.
 * 对不可控异常的处理
 */
public class ExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("An exception hash been captured");
        System.out.println("Thread: "+t.getId());
        System.out.printf("Exception: %s: %s\n", e.getClass().getName(), e.getMessage());
        System.out.println("Stack Trace:");
        e.printStackTrace(System.out);
        System.out.printf("Thread status :%s\n",t.getState());
    }
}
