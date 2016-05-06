package chapter_3.section_8;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Created by huojia on 2016/5/6 10:05.
 * Exchanger允许在并发任务之间交换数据，它允许在两个线程中定义同步点，
 * 当两个接口都到达同步点的时候将会交换数据，当第一个线程调用exchange()
 * 方法后，将会置于睡眠，直到第二个线程调用exchange()方法，可以适用于生产者和消费者场景。
 */
public class Core {
    public static void main(String[] args) {
        List<String> buffer1 = new ArrayList<>();
        List<String> buffer2 = new ArrayList<>();
        Exchanger<List<String>> exchanger = new Exchanger<>();
        Producer producer = new Producer(buffer1,exchanger);
        Consumer consumer = new Consumer(buffer2,exchanger);
        Thread threadProducer = new Thread(producer);
        Thread threadConsumer = new Thread(consumer);
        threadProducer.start();
        threadConsumer.start();
    }
}
