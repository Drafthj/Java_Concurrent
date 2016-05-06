package chapter_4.section_5;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by huojia on 2016/5/6 15:23.
 */
public class UserValidator {
    private String name;

    public UserValidator(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean validate(String name,String password){
        Random random = new Random();
        long duration = (long) (Math.random()*10);
        System.out.printf("Validator %s: Validating a user during %d seconds\n",this.name,duration);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            return false;
        }
        return random.nextBoolean();
    }
}
