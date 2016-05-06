package chapter_4.section_2;

/**
 * Created by huojia on 2016/5/6 10:43.
 */
public class Main {
    public static void main(String[] args) {
        Server server = new Server();
        for(int i = 0;i<100;i++){
            Task task = new Task("Task "+i);
            server.executeTask(task);
        }
        server.endServer();
    }
}
