package chapter_1;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by huojia on 2016/3/31 17:05.
 */
public class Calculator implements Runnable {
    private int number;

    public Calculator(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        for(int i=1;i<=10;i++){
            System.out.printf("%s: %d * %d = %d\n",Thread.currentThread().getName(),number,i,i*number);
        }
    }
    public static void writeThreadInfo(PrintWriter pw,Thread thread,Thread.State state){
        pw.printf("Main : ID %d - %s\r\n",thread.getId(),thread.getName());
        pw.printf("Main : Priority: %d\r\n",thread.getPriority());
        pw.printf("Main : Old State: %s\r\n",state);
        pw.printf("Main : New State: %s\r\n",thread.getState());
        pw.printf("Main : *********************************\n");
    }
    public static void main(String[] args) {
//        for(int i = 1;i<=10;i++){
//            Calculator calculator = new Calculator(i);
//            Thread thread = new Thread(calculator);
//            thread.start();
//        }
        Thread threads[] = new Thread[10];
        Thread.State status[] = new Thread.State[10];

        for(int i = 0;i<10;i++){
            threads[i] = new Thread(new Calculator(i));
            if(i%2==0){
                threads[i].setPriority(Thread.MAX_PRIORITY);
            }else {
                threads[i].setPriority(Thread.MIN_PRIORITY);
            }
            threads[i].setName("Thread "+i);
        }
        try (FileWriter fileWriter = new FileWriter("D:\\log.txt");
            PrintWriter pw = new PrintWriter(fileWriter);){
            for(int i = 0;i<10;i++){
                pw.print("Main : Status of Thread "+i+" : "+threads[i].getState());
                status[i] = threads[i].getState();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i = 0;i<10;i++){
            threads[i].start();
        }

        boolean finish = false;
        try (FileWriter fileWriter = new FileWriter("D:\\log.txt");
             PrintWriter pw = new PrintWriter(fileWriter);) {
            while (!finish) {
                for (int i = 0; i < 10; i++) {
                    if (threads[i].getState() != status[i]) {
                        writeThreadInfo(pw,threads[i],status[i]);
                        status[i] = threads[i].getState();
                    }
                }
                finish = true;
                for(int i = 0; i<10;i++){
                    finish = finish && (threads[i].getState() == Thread.State.TERMINATED);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
