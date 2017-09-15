package learn_1;

/**
 * 计算n/m的值
 * Created by txw on 2017/9/7.
 */
public class Data {

    private int m;
    private int n;


    public Data(int m, int n) {
        this.m = m;
        this.n = n;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    @Override
    public String toString() {
        int r = n / m;
        return n + "/" + m + "=" + r;
    }
}
