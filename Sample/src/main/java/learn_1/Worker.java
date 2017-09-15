package learn_1;

/**
 * 一、利用回调实现异步
 * Created by txw on 2017/9/7.
 */
public class Worker {

    public void doWork() {
        Fetcher fetcher = new MyFetcher(new Data(1, 0));
        fetcher.fetchData(new FetcherCallback() {
            public void onData(Data data) throws Exception {
                System.out.println("data received:" + data);
            }

            public void onError(Throwable cause) {
                System.out.println("error" + cause.getMessage());
            }
        });
    }


    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.doWork();
    }

}
