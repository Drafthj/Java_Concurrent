package chapter_1;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by huojia on 2016/4/1 15:40.
 * join()方法的使用，主线程在子线程执行完之后结束
 */
public class DataSourcesLoader implements Runnable{
    @Override
    public void run() {
        System.out.printf("Beginning data sources loading: %s\n", new Date());

        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Data Sources loading has finished: %s\n",new Date());
    }

    public static void main(String[] args) {
        DataSourcesLoader dsLoader = new DataSourcesLoader();
        Thread thread1 = new Thread(dsLoader,"DataSourceThread");
        NetWorkConnectionsLoader ncLoader = new NetWorkConnectionsLoader();
        Thread thread2 = new Thread(ncLoader,"NetWorkConnectionLoader");
        thread1.start();
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Main: Configuration has been loaded: %s\n", new Date());
    }
}
