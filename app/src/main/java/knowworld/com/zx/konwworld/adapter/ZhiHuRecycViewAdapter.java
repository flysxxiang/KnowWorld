package knowworld.com.zx.konwworld.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import knowworld.com.zx.konwworld.bean.Getposts;
import knowworld.com.zx.konwworld.R;
import knowworld.com.zx.konwworld.utils.imgaeloader.ImageLoaderFactory;

/**
 * @author fly_xiang_mac
 * @description
 * @time 2016-09-22
 */

public class ZhiHuRecycViewAdapter extends RecyclerView.Adapter<ZhiHuRecycViewAdapter.MyViewHolder> {
    private List<Getposts.AnswersBean> mDatas;
    private Context mContext;

    public ZhiHuRecycViewAdapter(Context context, List<Getposts.AnswersBean> list) {
        this.mDatas = list;
        this.mContext = context;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_zhihu_index, parent, false));// 如果使用parent,recyclerview只显示一行
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.tv_title.setText(mDatas.get(position).getTitle());
        holder.tv_content.setText(mDatas.get(position).getSummary());
        holder.tv_user.setText(mDatas.get(position).getAuthorname() + "  " + mDatas.get(position).getVote() + " 赞");
        ImageLoaderFactory.getLoader().displayCicleImage(holder.iv_avator, mDatas.get(position).getAvatar(), mContext);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_zhihu_tilte)
        TextView tv_title;
        @Bind(R.id.item_zhihu_content)
        TextView tv_content;
        @Bind(R.id.item_zhihu_user)
        TextView tv_user;
        @Bind(R.id.item_zhihu_avator)
        ImageView iv_avator;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
