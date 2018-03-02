package designPattern.builder;

/**
 * Created by Administrator on 2018/3/1.
 */
public abstract class Builder {

    public abstract void buildCpu(String cpu);

    public abstract void buildMainBoard(String mainBoard);

    public abstract void buildRam(String ram);

    public abstract MainFrame create();
}
