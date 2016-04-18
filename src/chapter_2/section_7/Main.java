package chapter_2.section_7;

import chapter_2.section_5.PrintQueue;

/**
 * Created by huojia on 2016/4/18 16:07.
 */
public class Main {
    public static void main(String[] args) {
        chapter_2.section_5.PrintQueue printQueue = new PrintQueue();
        Thread[] thread = new Thread[10];
        for(int i=0;i<10;i++){
            thread[i] = new Thread(new Job(printQueue),"Thread "+i);
        }
        for (int i = 0;i<10;i++){
            thread[i].start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
