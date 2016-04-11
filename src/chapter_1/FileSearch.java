package chapter_1;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by huojia on 2016/4/1 11:06.
 * 抛出InterruptedException异常来控制线程中断
 */
public class FileSearch implements Runnable{
    private String initPath;
    private String fileName;
    public FileSearch(String initPath,String fileName){
        this.initPath = initPath;
        this.fileName = fileName;
    }
    @Override
    public void run() {
        File file = new File(initPath);
        if(file.isDirectory()){
            try {
                directortyProcess(file);
            } catch (InterruptedException e) {
                System.out.printf("%s: The search has been interrupted", Thread.currentThread().getName());
            }
        }
    }

    private void directortyProcess(File file) throws InterruptedException {
        File list[] = file.listFiles();
        if(list != null){
            for(int i = 0;i<list.length;i++){
                if(list[i].isDirectory()){
                    directortyProcess(file);
                }else {
                    fileProcess(list[i]);
                }
            }
        }
        if(Thread.interrupted()){
            throw new InterruptedException();
        }
    }

    private void fileProcess(File file) throws InterruptedException {
        if(file.getName().equals(fileName)){
            System.out.printf("%s : %s\n", Thread.currentThread().getName(), file.getAbsolutePath());
        }
        if(Thread.interrupted()){
            throw new InterruptedException();
        }
    }

    public static void main(String[] args) {
        FileSearch searcher = new FileSearch("D:\\log","API_8552_20150728_0.log");
        Thread thread = new Thread(searcher);
        thread.start();

        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();
    }
}
