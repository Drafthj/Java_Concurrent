package chapter_4.section_6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by huojia on 2016/5/9 9:43.
 * 运行多个任务并处理所有结果
 * invokeAll()方法执行所有同步任务，并且等待所有任务完成返回所有结果
 */
public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Task> taskList = new ArrayList<>();
        for(int i = 0;i<3;i++){
            Task task = new Task(i+"");
            taskList.add(task);
        }
        List<Future<Result>>  resultList = null;
        try {
            resultList = executor.invokeAll(taskList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
        System.out.println("Main: Printing the results");
        for(int i = 0;i<resultList.size();i++){
            Future<Result> future = resultList.get(i);
            try {
                Result result = future.get();
                System.out.println(result.getName() + ": " + result.getValue());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
