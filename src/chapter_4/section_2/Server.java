package chapter_4.section_2;


import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by huojia on 2016/5/6 10:34.
 * ThreadPoolExecutor是线程执行器，推荐通过Executors工厂类来创建。
 * newCachedThreadPool()方法创建一个缓存线程池，缓存线程池会重用
 * 执行完毕后的线程，用来减少线程创建的时间，但是也有一定的缺点，新
 * 任务依赖于线程，当任务过多时候，会给系统造成负载。
 *
 * ThreadPoolExecutor需要显示的去关闭它，即使没有任务执行，也会一直
 * 等待新任务的来临，Java线程不会结束直到所有非守护线程结束为止，所以
 * Java应用程序将永远不会结束
 *
 * 适用于线程数量合理和任务执行时间很短的场景
 */
public class Server {
    private ThreadPoolExecutor executor;
    public Server(){
        executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
    }
    public void executeTask(Task task){
        System.out.printf("Server: A new task has arrived\n");
        executor.execute(task);
        System.out.printf("Server: Pool size: %d\n",executor.getPoolSize());
        System.out.printf("Server: Pool active count: %d\n",executor.getActiveCount());
        System.out.printf("Server: Pool completed count: %d\n",executor.getCompletedTaskCount());
    }
    public void endServer(){
        executor.shutdown();
    }
}
