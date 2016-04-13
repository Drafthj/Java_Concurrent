package chapter_2.section_3;

/**
 * Created by huojia on 2016/4/12 11:43.
 * synchronized关键字在代码块上的运用
 */
public class Cinema {
    private long vacanciesCinema1;
    private long vacanciesCinema2;
    private final Object controlCinemal,controlCinmea2;
    public Cinema(){
        controlCinemal = new Object();
        controlCinmea2 = new Object();
        vacanciesCinema1 = 20;
        vacanciesCinema2 =  20;
    }

    public boolean sellTickets1(int number){
        synchronized (controlCinemal){
            if(number<vacanciesCinema1){
                vacanciesCinema1 -= number;
                return true;
            }else {
                return false;
            }
        }
    }
    public boolean sellTickets2(int number){
        synchronized (controlCinmea2){
            if(number<vacanciesCinema2){
                vacanciesCinema2 -= number;
                return true;
            }else {
                return false;
            }
        }
    }
    public boolean returnTickets1(int number){
        synchronized (controlCinemal){
            vacanciesCinema1 += number;
            return true;
        }
    }
    public boolean returnTickets2(int number){
        synchronized (controlCinmea2){
            vacanciesCinema2 += number;
            return true;
        }
    }

    public long getVacanciesCinema1() {
        return vacanciesCinema1;
    }

    public long getVacanciesCinema2() {
        return vacanciesCinema2;
    }
}
