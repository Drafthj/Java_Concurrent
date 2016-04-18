package chapter_2.section_4;

/**
 * Created by huojia on 2016/4/18 15:15.
 * 生产者
 */
public class Producer implements Runnable{
    private EventStorage storage;

    public Producer(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for(int i = 0;i<100;i++){
            storage.set();
        }
    }
}
