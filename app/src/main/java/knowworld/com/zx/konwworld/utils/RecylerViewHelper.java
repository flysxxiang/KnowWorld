package knowworld.com.zx.konwworld.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.github.jdsjlzx.util.RecyclerViewStateUtils;
import com.github.jdsjlzx.view.LoadingFooter;

import knowworld.com.zx.konwworld.R;
import knowworld.com.zx.konwworld.listener.LRecylerviewListener;

/**
 * @author fly_xiang_mac
 * @description LReceylerView二次封装
 * @time 2016-09-26
 */

public class RecylerViewHelper {
    private LRecyclerView mRecyclerView;
    private Context mContext;
    private RecyclerView.Adapter mAdapter;
    private LRecylerviewListener mLRecylerviewListener;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private int mPageSize;


    public RecylerViewHelper (LRecyclerView recyclerView, Context context) {
        this.mRecyclerView = recyclerView;
        this.mContext = context;
        initRecylerView();
    }

    public void setAdapter (RecyclerView.Adapter adapter, LRecylerviewListener recylerviewListener) {
        this.mAdapter = adapter;
        this.mLRecylerviewListener = recylerviewListener;
        initAdapter();
    }

    /**
     *
     */
    private void initRecylerView () {
        // 设置recylerview模式
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);

        // 设置reyclerview下拉样式
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallTrianglePath); //设置下拉刷新Progress的样式
        mRecyclerView.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);  //设置下拉刷新箭头
    }

    /**
     *
     */
    private void initAdapter () {
        // 设置Recyclerview适配器与LRecyclerViewAdapter绑定
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mAdapter);
        mRecyclerView.setAdapter(mLRecyclerViewAdapter);

        // 监听点击事件
        mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick (View view, int i) {
                Log.i("recycler", "单击,位置" + i);
                mLRecylerviewListener.requstItemClick(view, i);
            }

            @Override
            public void onItemLongClick (View view, int i) {

            }
        });
    }

    /**
     * 强制刷新
     */
    public void forceToRefresh () {
        mRecyclerView.forceToRefresh();
    }

    /**
     * 刷新完成
     */
    public void refreComplete () {
        mRecyclerView.refreshComplete();
    }

    public void refreCompleteNoti () {
        mRecyclerView.refreshComplete();
        mLRecyclerViewAdapter.notifyDataSetChanged();
    }

    /**
     * 滑动到底部-----无数据
     *
     * @param pageSize
     */
    public void onBottomEnd (int pageSize) {
        this.mPageSize = pageSize;

        RecyclerViewStateUtils.setFooterViewState((Activity) mContext, mRecyclerView, pageSize, LoadingFooter.State.TheEnd, null);
    }

    /**
     * 滑动到底部-----正常模式
     *
     * @param pageSize
     */
    public void onBottomNormal (int pageSize) {
        this.mPageSize = pageSize;

        RecyclerViewStateUtils.setFooterViewState((Activity) mContext, mRecyclerView, pageSize, LoadingFooter.State.Normal, null);
    }


    /**
     * 滑动到底部-----加载失败
     *
     * @param pageSize
     */
    public void onBottomFail (int pageSize) {
        this.mPageSize = pageSize;

        RecyclerViewStateUtils.setFooterViewState((Activity) mContext, mRecyclerView, pageSize, LoadingFooter.State.NetWorkError, mFooterClick);
    }

    /**
     * 滑动到底部-----加载中
     *
     * @param pageSize
     */
    public void onBottomLoading (int pageSize) {
        this.mPageSize = pageSize;

        RecyclerViewStateUtils.setFooterViewState((Activity) mContext, mRecyclerView, pageSize, LoadingFooter.State.Loading, mFooterClick);
    }

    /**
     * 滑动到底部加载重试监听
     *
     * @param pageSize
     */
    private View.OnClickListener mFooterClick = new View.OnClickListener() {
        @Override
        public void onClick (View v) {
            mLRecylerviewListener.requestRetry();
            RecyclerViewStateUtils.setFooterViewState((Activity) mContext, mRecyclerView, mPageSize, LoadingFooter.State.Loading, null);
        }
    };
}
