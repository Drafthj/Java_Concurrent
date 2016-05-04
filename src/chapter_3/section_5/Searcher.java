package chapter_3.section_5;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by huojia on 2016/5/4 9:17.
 */
public class Searcher implements Runnable {
    private int firstRow;
    private int lastRow;
    private MatrixMock mock;
    private Results results;
    private int number;
    private final CyclicBarrier barrier;

    public Searcher(CyclicBarrier barrier, int number, Results results, MatrixMock mock, int lastRow, int firstRow) {
        this.barrier = barrier;
        this.number = number;
        this.results = results;
        this.mock = mock;
        this.lastRow = lastRow;
        this.firstRow = firstRow;
    }

    @Override
    public void run() {
        int counter;
        System.out.printf("%s: Processing lines from %d to %d.\n",Thread.currentThread().getName(),
                firstRow,lastRow);
        for(int i = firstRow;i<lastRow;i++){
            int row[] = mock.getRow(i);
            counter = 0;
            for(int j = 0;j<row.length;j++){
                if(row[j] == number){
                    counter++;
                }
            }
            results.setData(i,counter);
        }
        System.out.printf("%s: Lines processed.\n",Thread.currentThread().getName());
        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
