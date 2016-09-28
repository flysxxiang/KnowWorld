package knowworld.com.zx.konwworld.api;

import android.util.Log;

import knowworld.com.zx.konwworld.bean.BaseHttpResult;
import rx.Subscriber;

/**
 * @author fly_xiang_mac
 * @description RxJava线程切换封装, 做全局处理回调
 * @time 2016-09-26
 */

public abstract class HttpResultSubscriber<T> extends Subscriber<BaseHttpResult<T>> {
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

        Log.e("httpError", e.getMessage());
        requestFailure(e);

    }

    @Override
    public void onNext(BaseHttpResult<T> tBaseHttpResult) {
        // 根据服务端返回错误信息,正确的返回,error为""
        if (tBaseHttpResult.getShowapi_res_error().equals("")) {
            requestSuccess(tBaseHttpResult.getShowapi_res_body());
        } else {
            Throwable throwable = new Throwable("error:" + tBaseHttpResult.getShowapi_res_error());
            requestFailure(throwable);
            Log.e("httpError", throwable.getMessage());
        }
    }

    // 成功回调
    public abstract void requestSuccess(T t);

    // 失败回调
    public abstract void requestFailure(Throwable e);
}
