package chapter_3.section_8;

import java.util.List;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * Created by huojia on 2016/5/6 9:44.
 * 消费者
 */
public class Consumer implements Runnable {
    private List<String> buffer;
    private Exchanger<List<String>> exchanger;

    public Consumer(List<String> buffer, Exchanger<List<String>> exchanger) {
        this.buffer = buffer;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        int cycle = 1;
        for(int i=0;i<10;i++){
            System.out.printf("Consumer: Cycle %d\n",cycle);
            try {
                TimeUnit.SECONDS.sleep(3);
                buffer = exchanger.exchange(buffer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Consumer: "+buffer.size());
            for(int j=0;j<10;j++){
                String message = buffer.get(0);
                System.out.println("Consumer: "+message);
                buffer.remove(0);
            }
            cycle++;
        }
    }
}
