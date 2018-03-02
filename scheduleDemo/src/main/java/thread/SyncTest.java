package thread;

/**
 * Created by txw on 2018/2/27.
 */
public class SyncTest {

    public static void main(String[] args) {

        //重入锁以及条件对象
//        AliPay aliPay = new AliPay(2, 100);
//        try {
//            aliPay.transfer(0, 1, 150);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        //同步
        AliPay aliPay2 = new AliPay(3, 100);
        try {
            aliPay2.transfer2(0, 1, 10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //ArrayBlockingQueue

}
