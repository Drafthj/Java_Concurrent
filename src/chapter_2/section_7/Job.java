package chapter_2.section_7;

import chapter_2.section_5.PrintQueue;

/**
 * Created by huojia on 2016/4/18 15:59.
 */
public class Job implements Runnable {
    private chapter_2.section_5.PrintQueue printQueue;

    public Job(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }

    @Override
    public void run() {
        System.out.printf("%s: Going to print a document\n",Thread.currentThread().getName());
        printQueue.printJob(new Object());
        System.out.printf("%s: The document has been printed\n",Thread.currentThread().getName());
    }
}
