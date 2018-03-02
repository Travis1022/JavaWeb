package designPattern.builder;

/**
 * 生产商
 * Created by Administrator on 2018/3/1.
 */
public class Merchant extends Builder {

    private MainFrame mainFrame = new MainFrame();

    @Override
    public void buildCpu(String cpu) {
        mainFrame.setmCpu(cpu);
    }

    @Override
    public void buildMainBoard(String mainBoard) {
        mainFrame.setmMainBoard(mainBoard);
    }

    @Override
    public void buildRam(String ram) {
        mainFrame.setmRam(ram);
    }

    @Override
    public MainFrame create() {
        if (mainFrame != null) {
            System.out.println(mainFrame.getmCpu());
            System.out.println(mainFrame.getmMainBoard());
            System.out.println(mainFrame.getmRam());
            System.out.println("主机创建完成");
        }
        return mainFrame;

    }
}
