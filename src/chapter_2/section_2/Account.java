package chapter_2.section_2;

/**
 * Created by huojia on 2016/4/11 16:13.
 * synchronized关键字在方法上的使用
 */
public class Account {
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public synchronized void addAmount(double amount){
        double tmp = balance;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tmp += amount;
        balance = tmp;
    }
    public synchronized void subtractAmount(double amount){
        double tmp = balance;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tmp -= amount;
        balance = tmp;
    }
}
