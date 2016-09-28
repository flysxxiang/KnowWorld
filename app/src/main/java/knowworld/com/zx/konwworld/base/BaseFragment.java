package knowworld.com.zx.konwworld.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Map;
import java.util.Set;

import butterknife.ButterKnife;

/**
 * @author fly_xiang_mac
 * @description
 * @time 2016-09-18
 */
public abstract class BaseFragment extends Fragment {
    //创建View
    public abstract View setView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    //对视图进行操作
    public abstract void afterView ();

    protected View view;
    protected Activity mActivity;

    // 解决getActivity有时为null
    @Override
    public void onAttach (Activity activity) {
        super.onAttach(activity);
        this.mActivity = activity;
    }

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = setView(inflater, container, savedInstanceState);
        //在Fragment绑定ButterKnife注解框架
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated (View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        afterView();
    }

    @Override
    public void onDestroyView () {
        super.onDestroyView();
        //在Fragment解除绑定ButterKnife注解框架
        ButterKnife.unbind(this);
    }

    @Override
    public void onDetach () {
        super.onDetach();
        mActivity = null;
    }

    /*
     * Intent传递
     */
    public void intent (Class<?> cls) {
        startActivity(new Intent(getActivity(), cls));
    }

    protected void intent (Class<?> cls, Map<String, String> map) {
        Set<String> set = map.keySet();
        Intent intent = new Intent(getActivity(), cls);
        for (String key : set) {
            intent.putExtra(key, map.get(key));
        }
        startActivity(intent);
    }

}
