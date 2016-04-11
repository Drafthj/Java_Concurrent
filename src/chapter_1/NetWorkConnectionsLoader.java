package chapter_1;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by huojia on 2016/4/1 16:50.
 */
public class NetWorkConnectionsLoader implements Runnable{
    @Override
    public void run() {
        System.out.printf("Begining network connections loading: %s\n",new Date());
        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Network connections loading has finished: %s\n",new Date());
    }
}
