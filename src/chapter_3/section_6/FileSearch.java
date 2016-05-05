package chapter_3.section_6;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * Created by huojia on 2016/5/5 10:59.
 * Phaser主要用来分阶段处理线程同步问题，
 * 但是它是动态的，可以动态的减少线程。
 * 而且不必响应InterruptedException异常
 */
public class FileSearch implements Runnable{
    private String initPath;
    private String end;
    private List<String> results;
    private Phaser phaser;
    public FileSearch(String initPath, String end,Phaser phaser) {
        this.initPath = initPath;
        this.end = end;
        this.results = new ArrayList<>();
        this.phaser = phaser;
    }

    /**
     * 文件目录处理
     * @param file
     */
    private void directoryProcess(File file){
        File list[] = file.listFiles();
        if(list != null){
            for(int i=0;i<list.length;i++){
                if(list[i].isDirectory()){
                    directoryProcess(list[i]);
                }else {
                    fileProcess(list[i]);
                }
            }
        }
    }

    /**
     * 过滤第一阶段结果中二十四小时内修改过的文件
     */
    private void filterResults(){
        List<String> newResults = new ArrayList<>();
        long actualDate = new Date().getTime();
        for(int i = 0;i<results.size();i++){
            File file = new File(results.get(i));
            long fileDate = file.lastModified();
            if(actualDate-fileDate< TimeUnit.MILLISECONDS.convert(1,TimeUnit.DAYS)){
                newResults.add(results.get(i));
            }
        }
        results = newResults;
    }

    /**
     * 文件处理
     * @param file
     */
    private void fileProcess(File file){
        if(file.getName().endsWith(end)){
            results.add(file.getAbsolutePath());
        }
    }

    /**
     * 检查结果集是否为空，如果为空就从Phase中去掉线程，
     * 否则阻塞知道其他线程都执行完成这个阶段
     * @return
     */
    private boolean checkResults(){
        if(results.isEmpty()){
            System.out.printf("%s: Phase %d: 0 results.\n",Thread.currentThread().getName(),
                    phaser.getPhase());
            System.out.printf("%s: Phase %d: End.\n",Thread.currentThread().getName(),phaser.getPhase());;
            phaser.arriveAndDeregister();
            return false;
        }else {
            System.out.printf("%s: Phase %d:%d results.\n",Thread.currentThread().getName(),phaser.getPhase(),results.size());
            phaser.arriveAndAwaitAdvance();
            return true;
        }
    }

    /**
     * 打印结果集
     */
    public void showInfo(){
        for(int i = 0;i<results.size();i++){
            File file = new File(results.get(i));
            System.out.printf("%s: %s\n",Thread.currentThread().getName(),file.getAbsoluteFile());
        }
        phaser.arriveAndAwaitAdvance();
    }
    @Override
    public void run() {
        phaser.arriveAndAwaitAdvance();
        System.out.printf("%s: Starting.\n",Thread.currentThread().getName());
        File file = new File(initPath);
        if(file.isDirectory()){
            directoryProcess(file);
        }
        if(!checkResults()){
            return;
        }
        filterResults();
        if(!checkResults()){
            return;
        }
        showInfo();
        phaser.arriveAndDeregister();
        System.out.printf("%s: Work completed.\n",Thread.currentThread().getName());
    }


 }
