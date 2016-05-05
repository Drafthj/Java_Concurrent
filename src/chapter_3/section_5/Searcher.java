package chapter_3.section_5;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * Created by huojia on 2016/5/4 9:17.
 *  CyclicBarrier类有一个内部计数器，可以控制指定数目的几个线程都同时到达集合点。
 *  每一个线程到达集合点后就会调用await（）方法通知CyclicBarrier对象，CyclicB
 *  arrier对象会让这个线程休眠直到其他所有线程都到达集合点。
 *  当所有线程都到达集合点知乎，CyclicBarrier对象就会唤醒所有在await()方法里等待的
 *  线程，同时，还可以以构造器传入的Runnable对象创建一个新的线程，以执行其他任务。
 */
public class Searcher implements Runnable {
    private int firstRow;
    private int lastRow;
    private MatrixMock mock;
    private Results results;
    private int number;
    private final CyclicBarrier barrier;

    public Searcher(CyclicBarrier barrier, int number, Results results, MatrixMock mock, int firstRow, int lastRow) {
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
