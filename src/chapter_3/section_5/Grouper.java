package chapter_3.section_5;

/**
 * Created by huojia on 2016/5/4 16:58.
 */
public class Grouper implements Runnable {
    private Results results;

    public Grouper(Results results) {
        this.results = results;
    }

    @Override
    public void run() {
        int finalResult = 0;
    }
}
