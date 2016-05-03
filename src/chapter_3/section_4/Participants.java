package chapter_3.section_4;

import java.util.concurrent.TimeUnit;

/**
 * Created by huojia on 2016/5/3 16:15.
 * 参加者
 */
public class Participants implements Runnable {
    private VideoConference conference;
    private String name;

    public Participants(VideoConference conference, String name) {
        this.conference = conference;
        this.name = name;
    }

    @Override
    public void run() {
        long duration = (long) (Math.random()*10);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        conference.arrive(name);
    }
}
