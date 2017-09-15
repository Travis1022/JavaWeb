package learn_1;

/**
 * Created by txw on 2017/9/7.
 */
public interface FetcherCallback {
    void onData(Data data) throws Exception;

    void onError(Throwable cause);
}
