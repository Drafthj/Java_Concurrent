package chapter_4.section_3;


import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by huojia on 2016/5/6 10:34.
 * newFixedThreadPool()方法用来创建固定大小的线程池，
 * 当线程池满了之后，进来的新任务会一直阻塞到线程池有
 * 空闲线程为止。
 */
public class Server {
    private ThreadPoolExecutor executor;
    public Server(){
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
    }
    public void executeTask(Task task){
        System.out.printf("Server: A new task has arrived\n");
        executor.execute(task);
        System.out.printf("Server: Pool size: %d\n",executor.getPoolSize());
        System.out.printf("Server: Pool active count: %d\n",executor.getActiveCount());
        System.out.printf("Server: Pool completed count: %d\n",executor.getCompletedTaskCount());
        System.out.printf("Server: Task count: %d\n",executor.getTaskCount());
    }
    public void endServer(){
        executor.shutdown();
    }
}
