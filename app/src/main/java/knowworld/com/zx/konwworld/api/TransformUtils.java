package knowworld.com.zx.konwworld.api;


import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author fly_xiang_mac
 * @description
 * @time 2016-09-26
 */

public class TransformUtils {

    public static <T> Observable.Transformer<T, T> defaultSchedulers() {
        return new Observable.Transformer<T, T>() {

            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
            }
        };
    }

    public static <T> Observable.Transformer<T, T> alltoIo() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.observeOn(Schedulers.io()).subscribeOn(Schedulers.io());
            }
        };
    }
}
