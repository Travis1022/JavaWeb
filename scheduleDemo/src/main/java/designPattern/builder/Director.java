package designPattern.builder;

/**
 * 导演类
 * Created by txw on 2018/3/1.
 */
public class Director {

   /* 建造者模式
   *
   *
   *
   *
   *
   *
   *
   *
   * */

    Builder builder = null;


    public Director(Builder builder) {
        this.builder = builder;
    }


    public MainFrame createMainFrame(String cpu, String mainBoard, String ram) {
        this.builder.buildCpu(cpu);
        this.builder.buildMainBoard(mainBoard);
        this.builder.buildRam(ram);
        return builder.create();
    }
}
