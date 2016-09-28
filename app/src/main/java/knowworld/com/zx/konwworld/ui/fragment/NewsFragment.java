package knowworld.com.zx.konwworld.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import knowworld.com.zx.konwworld.R;
import knowworld.com.zx.konwworld.adapter.fragment_viewpager.SimpleFragmentPagerAdapter;
import knowworld.com.zx.konwworld.base.BaseFragment;

/**
 * @author fly_xiang_mac
 * @description
 * @time 2016-09-18
 */
public class NewsFragment extends BaseFragment {
    @Bind(R.id.news_tabs)
    TabLayout mTabLayout;
    @Bind(R.id.news_vp)
    ViewPager mViewPager;
    private SimpleFragmentPagerAdapter adapter;

    @Override
    public View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, null);
        return view;
    }

    @Override
    public void afterView() {
        adapter = new SimpleFragmentPagerAdapter(getChildFragmentManager(), getActivity(), getResources().getStringArray(R.array.title_news));
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(8);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
