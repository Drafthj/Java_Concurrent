package chapter_3.section_5;

/**
 * Created by huojia on 2016/5/4 9:13.
 */
public class Results {
    private int data[];

    public Results(int size) {
        data = new int[size];
    }

    public void setData(int position,int value){
        data[position] = value;
    }

    public int[] getData() {
        return data;
    }
}
