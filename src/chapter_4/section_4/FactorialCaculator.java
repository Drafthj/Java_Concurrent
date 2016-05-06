package chapter_4.section_4;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by huojia on 2016/5/6 11:40.
 * Callable接口来启动并发任务，并返回结果
 * 可以取消任务和检查任务完成
 */
public class FactorialCaculator implements Callable<Integer>{
    private Integer number;

    public FactorialCaculator(Integer number) {
        this.number = number;
    }

    @Override
    public Integer call() throws Exception {
        int result = 1;
        if(number == 0||number ==1){
            return 1;
        }else {
            for(int i = 2;i<=number;i++){
                result *= i;
                TimeUnit.MILLISECONDS.sleep(20);
            }
        }
        System.out.printf("%s: %d\n",Thread.currentThread().getName(),result);
        return result;
    }
}
