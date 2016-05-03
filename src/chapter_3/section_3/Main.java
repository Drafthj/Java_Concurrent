package chapter_3.section_3;


/**
 * Created by huojia on 2016/4/19 16:24.
 */
public class Main {
    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new Job(printQueue),"Thread"+i);
        }
        for(int i = 0;i<10;i++){
            threads[i].start();
        }
    }
}
