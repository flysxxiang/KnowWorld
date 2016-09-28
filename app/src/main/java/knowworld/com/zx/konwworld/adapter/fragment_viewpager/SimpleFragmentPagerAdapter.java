package knowworld.com.zx.konwworld.adapter.fragment_viewpager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import knowworld.com.zx.konwworld.R;
import knowworld.com.zx.konwworld.ui.fragment.NewsLsitFragment;

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] tabTitles;
    private String[] tabChannelId;
    private Context mContext;


    public SimpleFragmentPagerAdapter(FragmentManager fm, Context context, String[] tabTitles) {
        super(fm);
        this.mContext = context;
        this.tabTitles = tabTitles;
        tabChannelId = mContext.getResources().getStringArray(R.array.channelId_news);
    }

    @Override
    public Fragment getItem(int position) {

        return NewsLsitFragment.newInstance(tabChannelId[position]);
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        //重载该方法，防止其它视图被销毁，防止加载视图卡顿
        super.destroyItem(container, position, object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

}