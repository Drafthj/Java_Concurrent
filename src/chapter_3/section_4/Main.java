package chapter_3.section_4;

/**
 * Created by huojia on 2016/5/3 16:22.
 */
public class Main {
    public static void main(String[] args) {
        VideoConference conference = new VideoConference(10);
        Thread threadConference = new Thread(conference);
        threadConference.start();

        for(int i = 0;i<10;i++){
            Participants participants = new Participants(conference,"Participants "+i);
            Thread t = new Thread(participants);
            t.start();
        }
    }
}
