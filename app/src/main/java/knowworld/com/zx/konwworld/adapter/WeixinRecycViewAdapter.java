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
import knowworld.com.zx.konwworld.bean.WeixinBean;
import knowworld.com.zx.konwworld.utils.imgaeloader.ImageLoaderFactory;

/**
 * @author fly_xiang_mac
 * @description
 * @time 2016-09-22
 */

public class WeixinRecycViewAdapter extends ListBaseAdapter<WeixinBean.NewslistBean> {

    private Context mContext;

    public WeixinRecycViewAdapter (Context context, List<WeixinBean.NewslistBean> list) {
        mDataList = (ArrayList<WeixinBean.NewslistBean>) list;
        this.mContext = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {

        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weixin_index, parent, false));// 如果使用parent,recyclerview只显示一行
    }

    @Override
    public void onBindViewHolder (RecyclerView.ViewHolder viewHolder, int position) {
        MyViewHolder holder = (MyViewHolder) viewHolder;

        holder.tv_title.setText(mDataList.get(position).getTitle());
        holder.tv_des.setText(mDataList.get(position).getDescription());
        holder.tv_time.setText(mDataList.get(position).getCtime());
        ImageLoaderFactory.getLoader().displayImage(holder.iv_bg, mDataList.get(position).getPicUrl(), mContext);
    }

    @Override
    public int getItemCount () {
        return mDataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_weixin_iv)
        ImageView iv_bg;
        @Bind(R.id.item_weixin_title)
        TextView tv_title;
        @Bind(R.id.item_weixin_des)
        TextView tv_des;
        @Bind(R.id.item_weixin_time)
        TextView tv_time;

        public MyViewHolder (View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
