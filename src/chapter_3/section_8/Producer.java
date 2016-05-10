package chapter_3.section_8;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Created by huojia on 2016/5/6 9:32.
 * 生产者
 */
public class Producer implements Runnable {
    private List<String> buffer;
    private Exchanger<List<String>> exchanger;

    public Producer(List<String> buffer, Exchanger<List<String>> exchanger) {
        this.buffer = buffer;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        int cycle = 1;
        for(int i = 0;i<10;i++){
            System.out.printf("Producer: Cycle %d\n",cycle);
            for(int j=0;j<10;j++){
                String message = "Event "+((i*10)+j);
                System.out.printf("Producer: %s\n",message);
                buffer.add(message);
            }
            try {
                exchanger.exchange(buffer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Producer :"+buffer.size());
            cycle++;
        }
    }
}
