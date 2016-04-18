package chapter_2.section_4;

/**
 * Created by huojia on 2016/4/18 15:31.
 */
public class Consumer implements Runnable {
    private EventStorage storage;

    public Consumer(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for(int i = 0;i<100;i++){
         storage.get();
        }
    }
}
