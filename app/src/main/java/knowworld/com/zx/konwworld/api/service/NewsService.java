package knowworld.com.zx.konwworld.api.service;

import knowworld.com.zx.konwworld.bean.BaseHttpResult;
import knowworld.com.zx.konwworld.bean.NewsBean;
import knowworld.com.zx.konwworld.bean.NewsDtails;
import knowworld.com.zx.konwworld.bean.WeixinBean;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author fly_xiang_mac
 * @description
 * @time 2016-09-20
 */

public interface NewsService {

    String HOST = "http://route.showapi.com/";

    // 新闻
    @GET("109-35?showapi_appid=24587&showapi_sign=b03538e8f870457ebd7250714b300622")
    Observable<BaseHttpResult<NewsBean>> NewsRepos (@Query("channelId") String channelId, @Query("page") String pages);

    // url正文转api
    @GET("883-1?showapi_appid=24587&showapi_sign=b03538e8f870457ebd7250714b300622")
    Observable<NewsDtails> NewsDetails (@Query("url") String url);

    // 微信精选
    @GET("181-1?showapi_appid=24587&showapi_sign=b03538e8f870457ebd7250714b300622&num=15")
    Observable<BaseHttpResult<WeixinBean>> WeixinRepos (@Query("page") String pages);

}
