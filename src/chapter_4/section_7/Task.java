package chapter_4.section_7;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 * Created by huojia on 2016/5/9 10:14.
 */
public class Task implements Callable<String> {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        System.out.printf("%s :start at %s\n",this.name,new Date());
        return "Hello World!";
    }
}
