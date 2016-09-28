package knowworld.com.zx.konwworld.api.service;

import knowworld.com.zx.konwworld.bean.Getposts;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @author fly_xiang_mac
 * @description
 * @time 2016-09-17
 */
public interface ZhiHuService {

    String HOST = "http://api.kanzhihu.com/";

    @GET("getpostanswers/{data}/yesterday")
    Observable<Getposts> ZhihuRepos(@Path("data") String data);
}
