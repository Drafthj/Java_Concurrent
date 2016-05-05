package chapter_3.section_5;

import java.util.concurrent.CyclicBarrier;

/**
 * Created by huojia on 2016/5/5 9:37.
 */
public class Main {
    public static void main(String[] args) {
        final int ROWS = 10000;
        final int NUMBERS = 1000;
        final int SEARCH = 5;
        final int PARTICIPANTS = 5;
        final int LINES_PARTICIPANTS = 2000;
        MatrixMock mock = new MatrixMock(ROWS,NUMBERS,SEARCH);
        Results results = new Results(ROWS);
        Grouper grouper = new Grouper(results);
        //将等五个线程结束后执行grouper线程
        CyclicBarrier barrier = new CyclicBarrier(PARTICIPANTS,grouper);
        Searcher searchers[] = new Searcher[5];
        for(int i = 0;i<PARTICIPANTS;i++){
            searchers[i] = new Searcher(barrier,SEARCH,results,mock,i*LINES_PARTICIPANTS,(i+1)*LINES_PARTICIPANTS);
            Thread thread = new Thread(searchers[i]);
            thread.start();
        }
        barrier.reset();
        System.out.println("Main: The main thread has finished.\n");
    }
}
