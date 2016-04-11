package chapter_1;

/**
 * Created by huojia on 2016/4/8 11:31.
 * 测试UncaughtExceptionHandler
 */
public class Task implements Runnable {
    @Override
    public void run() {
        int numero = Integer.parseInt("TTT");
    }

    public static void main(String[] args) {
        Task task = new Task();
        Thread thread = new Thread(task);
        thread.setUncaughtExceptionHandler(new ExceptionHandler());
        thread.start();
    }
}
