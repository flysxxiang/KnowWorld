package knowworld.com.zx.konwworld.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.recyclerview.LRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import knowworld.com.zx.konwworld.R;
import knowworld.com.zx.konwworld.adapter.WeixinRecycViewAdapter;
import knowworld.com.zx.konwworld.api.HttpResultSubscriber;
import knowworld.com.zx.konwworld.api.RetrofitHelper;
import knowworld.com.zx.konwworld.api.TransformUtils;
import knowworld.com.zx.konwworld.base.BaseFragment;
import knowworld.com.zx.konwworld.bean.BaseHttpResult;
import knowworld.com.zx.konwworld.bean.WeixinBean;
import knowworld.com.zx.konwworld.listener.LRecylerviewListener;
import knowworld.com.zx.konwworld.ui.activity.WeixinDetailsActivity;
import knowworld.com.zx.konwworld.utils.RecylerViewHelper;
import rx.Observable;

/**
 * @author fly_xiang_mac
 * @description
 * @time 2016-09-22
 */

public class WeiXinFragment extends BaseFragment {

    @Bind(R.id.zhihu_recyc)
    LRecyclerView mRecyclerView;
    private List<WeixinBean.NewslistBean> mDatas;
    private RecylerViewHelper mRecylerViewHelper;
    private WeixinRecycViewAdapter mAdapter;
    private int mPage = 1;
    private int mPageSize = 15;

    @Override
    public View setView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyelerview2, null);

        return view;
    }

    @Override
    public void afterView () {

        mRecylerViewHelper = new RecylerViewHelper(mRecyclerView, mActivity);
        mDatas = new ArrayList<WeixinBean.NewslistBean>();
        initLScrollListenenr();
        mRecylerViewHelper.forceToRefresh();
    }

    private void loadData (int page) {
        Observable<BaseHttpResult<WeixinBean>> observable = RetrofitHelper.getInstance().getNewsRetrofit().WeixinRepos(page + "");
        observable.compose(TransformUtils.<BaseHttpResult<WeixinBean>>defaultSchedulers())
                .subscribe(new HttpResultSubscriber<WeixinBean>() {

                    @Override
                    public void requestSuccess (WeixinBean results) {
                        if (mAdapter == null) {

                            mRecyclerView.refreshComplete();
                            mDatas.addAll(results.getNewslist());
                            mAdapter = new WeixinRecycViewAdapter(mActivity, mDatas);
                            mRecylerViewHelper.setAdapter(mAdapter, new LRecylerviewListener() {
                                @Override
                                public void requstItemClick (View view, int i) {
                                    intentActivity(mDatas.get(i).getUrl());
                                }

                                @Override
                                public void requestRetry () {
                                    loadNextData(mPage);
                                }
                            });
                        } else {

                            mRecylerViewHelper.refreCompleteNoti();
                            mAdapter.setDataList(results.getNewslist());
                        }

                        mPage = 2;
                    }

                    @Override
                    public void requestFailure (Throwable e) {

                        mRecylerViewHelper.refreComplete();
                    }
                });
    }

    private void loadNextData (final int page) {
        Observable<BaseHttpResult<WeixinBean>> observable = RetrofitHelper.getInstance().getNewsRetrofit().WeixinRepos(page + "");
        observable.compose(TransformUtils.<BaseHttpResult<WeixinBean>>defaultSchedulers())
                .subscribe(new HttpResultSubscriber<WeixinBean>() {

                    @Override
                    public void requestSuccess (WeixinBean results) {

                        mAdapter.addAll(results.getNewslist());
                        mRecylerViewHelper.refreComplete();
                        if (mDatas.size() < page * mPageSize) {
                            mRecylerViewHelper.onBottomEnd(mPageSize);
                        }
//                        else {
//                            mRecylerViewHelper.onBottomNormal(mPageSize);
//                        }
                        mPage += 1;
                    }

                    @Override
                    public void requestFailure (Throwable e) {
                        mRecylerViewHelper.onBottomFail(mPageSize);
                    }
                });
    }


    // 监听滑动事件
    public void initLScrollListenenr () {
        mRecyclerView.setLScrollListener(new LRecyclerView.LScrollListener() {
            @Override
            public void onRefresh () {// 下拉刷新事件

                loadData(1);
            }

            @Override
            public void onScrollUp () {// 向上滑动监听

            }

            @Override
            public void onScrollDown () {// 向下滑动监听

            }

            @Override
            public void onBottom () {// 滑动到底部
                mRecylerViewHelper.onBottomLoading(mPageSize);
                loadNextData(mPage);
            }

            @Override
            public void onScrolled (int i, int i1) {

            }

            @Override
            public void onScrollStateChanged (int i) {

            }
        });
    }

    private void intentActivity (String url) {
        Intent intent = new Intent(mActivity, WeixinDetailsActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }
}