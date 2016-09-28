package knowworld.com.zx.konwworld.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.jdsjlzx.recyclerview.LRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import knowworld.com.zx.konwworld.R;
import knowworld.com.zx.konwworld.adapter.NewsRecyclerViewAdapter;
import knowworld.com.zx.konwworld.api.HttpResultSubscriber;
import knowworld.com.zx.konwworld.api.RetrofitHelper;
import knowworld.com.zx.konwworld.api.TransformUtils;
import knowworld.com.zx.konwworld.base.BasePageFragment;
import knowworld.com.zx.konwworld.bean.BaseHttpResult;
import knowworld.com.zx.konwworld.bean.NewsBean;
import knowworld.com.zx.konwworld.listener.LRecylerviewListener;
import knowworld.com.zx.konwworld.ui.activity.NewsDetailsActivity;
import knowworld.com.zx.konwworld.utils.RecylerViewHelper;
import rx.Observable;

/**
 * @author fly_xiang_mac
 * @description
 * @time 2016-09-20
 */
public class NewsLsitFragment extends BasePageFragment {

    @Bind(R.id.newlsit_recyclerView)
    LRecyclerView mRecyclerView;
    @Bind(R.id.emptyview)
    TextView mEmptyview;

    public static final String ARGS_CHANNELID = "ARG_PAGE";
    private String mChanneId;

    private List<NewsBean.PagebeanBean.ContentlistBean> mDatas;
    private RecylerViewHelper mRecylerViewHelper;
    private NewsRecyclerViewAdapter mAdapter;
    private int mPage = 1;
    private int mPageSize = 15;


    public static NewsLsitFragment newInstance (String channelId) {
        NewsLsitFragment mFragment = new NewsLsitFragment();
        Bundle args = new Bundle();
        args.putString(ARGS_CHANNELID, channelId);
        mFragment.setArguments(args);
        return mFragment;
    }

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newslist, null);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void fetchData () {
        if (getArguments() != null) {
            mChanneId = getArguments().getString(ARGS_CHANNELID);
        }

        mRecyclerView.setEmptyView(mEmptyview);
        mRecylerViewHelper = new RecylerViewHelper(mRecyclerView, getActivity());
        mDatas = new ArrayList<NewsBean.PagebeanBean.ContentlistBean>();
        initLScrollListenenr();
        mRecylerViewHelper.forceToRefresh();
    }

    private void loadData (int page) {

        Observable<BaseHttpResult<NewsBean>> observable = RetrofitHelper.getInstance().getNewsRetrofit().NewsRepos(mChanneId, page + "");
        observable.compose(TransformUtils.<BaseHttpResult<NewsBean>>defaultSchedulers())
                .subscribe(new HttpResultSubscriber<NewsBean>() {
                    @Override
                    public void requestSuccess (NewsBean results) {
                        if (mAdapter == null) {

                            mRecyclerView.refreshComplete();
                            mDatas.addAll(results.getPagebean().getContentlist());
                            mAdapter = new NewsRecyclerViewAdapter(getActivity(), mDatas);
                            mRecylerViewHelper.setAdapter(mAdapter, new LRecylerviewListener() {
                                @Override
                                public void requstItemClick (View view, int i) {
                                    intentActivity(mDatas.get(i).getLink());
                                }

                                @Override
                                public void requestRetry () {
                                    loadNextData(mPage);
                                }
                            });
                        } else {
                            mRecylerViewHelper.refreCompleteNoti();
                            mAdapter.setDataList(results.getPagebean().getContentlist());
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
        Observable<BaseHttpResult<NewsBean>> observable = RetrofitHelper.getInstance().getNewsRetrofit().NewsRepos(mChanneId, page + "");
        observable.compose(TransformUtils.<BaseHttpResult<NewsBean>>defaultSchedulers())
                .subscribe(new HttpResultSubscriber<NewsBean>() {

                    @Override
                    public void requestSuccess (NewsBean results) {

                        mAdapter.addAll(results.getPagebean().getContentlist());
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
        Intent intent = new Intent(getActivity(), NewsDetailsActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }

    @Override
    public void onDestroyView () {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}