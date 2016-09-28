package knowworld.com.zx.konwworld.api;

import java.util.concurrent.TimeUnit;

import knowworld.com.zx.konwworld.api.service.NewsService;
import knowworld.com.zx.konwworld.api.service.ZhiHuService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author fly_xiang_mac
 * @description Retrofit网络请求类,配置OkHttp
 * @time 2016-08-18
 */
public class RetrofitHelper {
    private OkHttpClient okHttpClient = null;

    // 超时时间
    private static final int CONNECT_TIMEOUT = 10;
    private static final int READ_TIMEOUT = 10;
    private static final int WRITE_TIMEOUT = 10;

    private static class SingletonHolder {
        private static final RetrofitHelper retrofinHelper = new RetrofitHelper();
    }

    public static RetrofitHelper getInstance() {
        return SingletonHolder.retrofinHelper;
    }

    public RetrofitHelper() {

    }

    public ZhiHuService getZhiHuRetrofit() {
        Retrofit ZhiHuRetrofit = new Retrofit.Builder()
                .baseUrl(ZhiHuService.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //使用RxJava作为回调适配器
                .client(getOkHttpClient())
                .build();

        return ZhiHuRetrofit.create(ZhiHuService.class);
    }

    public NewsService getNewsRetrofit() {
        Retrofit newsService = new Retrofit.Builder()
                .baseUrl(NewsService.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) //使用RxJava作为回调适配器
                .client(getOkHttpClient())
                .build();

        return newsService.create(NewsService.class);
    }


    private OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .addInterceptor(getHttpLoggingInterceptor())
                    .build();

            return okHttpClient;
        }

        return okHttpClient;
    }


    /**
     * 设置打印Log信息拦截器
     *
     * @return
     */
    public static HttpLoggingInterceptor getHttpLoggingInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);// 四种等级
        return loggingInterceptor;
    }
}
