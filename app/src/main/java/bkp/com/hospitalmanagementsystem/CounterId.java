package bkp.com.hospitalmanagementsystem;

public class CounterId {
    public static int counter = 1001;

    public CounterId(int counter) {
        this.counter = counter;
    }

    public CounterId() {

    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
