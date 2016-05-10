package chapter_4.section_5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by huojia on 2016/5/6 15:42.
 * 主线程
 * ExecutorService的invokeAny()方法会返回
 * 第一个执行完并且没有抛出异常的结果。
 *
 * 如果都抛出异常，ExecutorService也将抛出异常
 */
public class Main {
    public static void main(String[] args) {
        String username = "test";
        String password = "test";
        UserValidator ldapValidation = new UserValidator("LDAP");
        UserValidator dbValidation = new UserValidator("Database");
        TaskValidator ldapTask = new TaskValidator(ldapValidation,username,password);
        TaskValidator dbTask = new TaskValidator(dbValidation,username,password);
        List<TaskValidator> taskList = new ArrayList<>();
        taskList.add(ldapTask);
        taskList.add(dbTask);
        ExecutorService executorService = Executors.newCachedThreadPool();
        String result;
        try {
            result = executorService.invokeAny(taskList);
            System.out.printf("Main: Result: %s\n",result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        System.out.printf("Main: End of the Executor");
    }
}
