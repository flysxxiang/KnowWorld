package knowworld.com.zx.konwworld.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import java.util.List;

import butterknife.Bind;
import knowworld.com.zx.konwworld.R;
import knowworld.com.zx.konwworld.api.RetrofitHelper;
import knowworld.com.zx.konwworld.base.BaseActivity;
import knowworld.com.zx.konwworld.bean.NewsDtails;
import knowworld.com.zx.konwworld.utils.imgaeloader.ImageLoaderFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author fly_xiang_mac
 * @description
 * @time 2016-09-22
 */

public class NewsDetailsActivity extends BaseActivity {

    @Bind(R.id.newsdetails_iv)
    ImageView iv_tool;
    @Bind(R.id.newsdetails_view)
    View newsdetailsView;
    @Bind(R.id.newsdtails_toolbar)
    Toolbar mToolbar;
    @Bind(R.id.newsdetails_toollayout)
    CollapsingToolbarLayout mToolbarLayout;
    @Bind(R.id.newsdetails_bar)
    AppBarLayout newsdetailsBar;
    @Bind(R.id.newsdetails_webview)
    WebView mWebview;

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsdetails);
        String url = getIntent().getStringExtra("url");

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                NewsDetailsActivity.this.finish();
            }
        });

        Observable<NewsDtails> observable = RetrofitHelper.getInstance().getNewsRetrofit().NewsDetails(url);
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Subscriber<NewsDtails>() {

                            @Override
                            public void onCompleted () {
                            }

                            @Override
                            public void onError (Throwable e) {

                            }

                            @Override
                            public void onNext (NewsDtails retrofitEntity) {

                                String html = "<head><style>img{width:320px !important;}</style></head>" + retrofitEntity.getShowapi_res_body().getHtml();
                                mWebview.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null);

                                mToolbarLayout.setTitle(retrofitEntity.getShowapi_res_body().getTitle());
                                // 扩张时候的title颜色
                                //mToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, R.color.colorBg));
                                //mToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.bottomTextColor));

                                List<NewsDtails.ShowapiResBodyBean.ImgListBean> img_list = retrofitEntity.getShowapi_res_body().getImg_list();
                                if (img_list.size() >= 1) {
                                    ImageLoaderFactory.getLoader().displayImage(iv_tool, img_list.get(0).getUrl(), NewsDetailsActivity.this);
                                }
                            }
                        }
                );
    }

}
