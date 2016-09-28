package knowworld.com.zx.konwworld.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import knowworld.com.zx.konwworld.R;
import knowworld.com.zx.konwworld.bean.NewsBean;
import knowworld.com.zx.konwworld.utils.imgaeloader.ImageLoaderFactory;

/**
 * @author fly_xiang_mac
 * @description 根据数据图片多少判断什么类型
 * @time 2016-09-20
 */

public class NewsRecyclerViewAdapter extends ListBaseAdapter<NewsBean.PagebeanBean.ContentlistBean> {
    private static final int TYPE_NOIMG = 0;// 无图模式
    private static final int TYPE_ONEIMG = 1;// 单图模式
    private static final int TYPE_THREEIMG = 2;// 三图模式

    private Context mContext;

    public NewsRecyclerViewAdapter (Context context, List<NewsBean.PagebeanBean.ContentlistBean> list) {
        mDataList = (ArrayList<NewsBean.PagebeanBean.ContentlistBean>) list;
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case TYPE_NOIMG:
                view = getView(parent, R.layout.item_news_recyclerview1);
                return new NoImgViewHolder(view);

            case TYPE_ONEIMG:
                view = getView(parent, R.layout.item_news_recyclerview2);
                return new OneImgViewHolder(view);

            case TYPE_THREEIMG:
                view = getView(parent, R.layout.item_news_recyclerview3);
                return new ThreeImgViewHolder(view);

            default:
                throw new RuntimeException("no" + viewType + "c");
        }
    }

    protected View getView (ViewGroup parent, int layoutId) {
        return LayoutInflater.from(parent.getContext()).inflate(layoutId, null, false);// 如果使用parent,recyclerview只显示一行
    }

    /**
     * 设置不同类型item
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType (int position) {
        if (mDataList.get(position).getImageurls().size() == 0) {
            return TYPE_NOIMG;
        } else if (mDataList.get(position).getImageurls().size() >= 1 && mDataList.get(position).getImageurls().size() < 3) {
            return TYPE_ONEIMG;
        } else {
            return TYPE_THREEIMG;
        }
    }

    @Override
    public int getItemCount () {
        return mDataList.size();
    }

    @Override
    public void onBindViewHolder (RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NoImgViewHolder) {

            setNoImgData((NoImgViewHolder) holder, position);
        } else if (holder instanceof OneImgViewHolder) {

            setOneImgData((OneImgViewHolder) holder, position);
        } else if (holder instanceof ThreeImgViewHolder) {

            setThreeImgData((ThreeImgViewHolder) holder, position);
        }
    }

    private void setNoImgData (NoImgViewHolder holder, int position) {

        holder.tv_title.setText(mDataList.get(position).getTitle());
        holder.tv_des.setText(mDataList.get(position).getDesc());
        holder.tv_time.setText(mDataList.get(position).getSource() + "   " + mDataList.get(position).getPubDate());
    }

    class NoImgViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_news_title)
        TextView tv_title;
        @Bind(R.id.item_news_des)
        TextView tv_des;
        @Bind(R.id.item_news_time)
        TextView tv_time;

        public NoImgViewHolder (View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

    private void setOneImgData (OneImgViewHolder holder, int position) {

        holder.tv_title.setText(mDataList.get(position).getTitle());
        holder.tv_time.setText(mDataList.get(position).getSource() + "   " + mDataList.get(position).getPubDate());

        ImageLoaderFactory.getLoader().displayImage(holder.iv_img, mDataList.get(position).getImageurls().get(0).getUrl(), mContext);
    }

    class OneImgViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_news_title)
        TextView tv_title;
        @Bind(R.id.item_news_img)
        ImageView iv_img;
        @Bind(R.id.item_news_time)
        TextView tv_time;

        public OneImgViewHolder (View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }


    private void setThreeImgData (ThreeImgViewHolder holder, int position) {
        holder.tv_title.setText(mDataList.get(position).getTitle());
        holder.tv_time.setText(mDataList.get(position).getSource() + "   " + mDataList.get(position).getPubDate());

        ImageLoaderFactory.getLoader().displayImage(holder.iv_img1, mDataList.get(position).getImageurls().get(0).getUrl(), mContext);
        ImageLoaderFactory.getLoader().displayImage(holder.iv_img2, mDataList.get(position).getImageurls().get(1).getUrl(), mContext);
        ImageLoaderFactory.getLoader().displayImage(holder.iv_img3, mDataList.get(position).getImageurls().get(2).getUrl(), mContext);

    }

    class ThreeImgViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_news_title)
        TextView tv_title;
        @Bind(R.id.item_news_img1)
        ImageView iv_img1;
        @Bind(R.id.item_news_img2)
        ImageView iv_img2;
        @Bind(R.id.item_news_img3)
        ImageView iv_img3;
        @Bind(R.id.item_news_time)
        TextView tv_time;


        public ThreeImgViewHolder (View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }
}
