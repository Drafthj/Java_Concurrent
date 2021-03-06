package chapter_2.section_8;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by huojia on 2016/4/19 9:20.
 * 数据缓冲类
 * 通过condition对象来进行多条件资源控制
 */
public class Buffer{
    private LinkedList<String> buffer;
    private int maxSize;
    private ReentrantLock lock;
    private Condition lines;
    private Condition spaces;
    /**
     * 表明缓冲区是否还有数据
     */
    private boolean pendingLines;

    public Buffer(int maxSize) {
        this.maxSize = maxSize;
        buffer = new LinkedList<>();
        lock = new ReentrantLock();
        lines = lock.newCondition();
        spaces = lock.newCondition();
        pendingLines = true;
    }
    public void insert(String line){
        lock.lock();
            try {
                while (buffer.size() == maxSize){
                    spaces.await();
                }
                buffer.offer(line);
                System.out.printf("%s: Inserted Line: %d\n",Thread.currentThread().getName(),buffer.size());
                lines.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
    }

    public String get(){
        String line = null;
        lock.lock();
        try {
            while ((buffer.size() == 0)&&(hasPendingLines())){
                lines.await();
            }
            if(hasPendingLines()){
                line = buffer.poll();
                System.out.printf("%s: Line Readed: %d\n",Thread.currentThread().getName(),buffer.size());
                spaces.signalAll();
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return line;
    }

    public void setPendingLines(boolean pendingLines) {
        this.pendingLines = pendingLines;
    }

    public boolean hasPendingLines(){
        return pendingLines||buffer.size()>0;
    }
}
