package designPattern.builder;

/**
 * 主机
 * Created by Administrator on 2018/3/1.
 */
public class MainFrame {

    private String mCpu;

    private String mMainBoard;

    private String mRam;


    public String getmCpu() {
        return mCpu;
    }

    public void setmCpu(String mCpu) {
        this.mCpu = mCpu;
    }

    public String getmMainBoard() {
        return mMainBoard;
    }

    public void setmMainBoard(String mMainBoard) {
        this.mMainBoard = mMainBoard;
    }

    public String getmRam() {
        return mRam;
    }

    public void setmRam(String mRam) {
        this.mRam = mRam;
    }
}
