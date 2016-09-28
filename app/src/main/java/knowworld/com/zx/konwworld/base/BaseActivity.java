package knowworld.com.zx.konwworld.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.util.Map;
import java.util.Set;

import butterknife.ButterKnife;
import knowworld.com.zx.konwworld.AppManager;
import knowworld.com.zx.konwworld.utils.LogUtils;

/**
 * 创建： 2016/2/22 11:19
 * 备注： 所有Activity的父类
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.i("" + this.getClass().getName(), " onCreat");

        //将Activity加入AppManager堆栈中
        AppManager.getAppManager().addActivity(this);
    }

    @Override
    public void setContentView (int layoutResID) {
        super.setContentView(layoutResID);
        LogUtils.i("" + this.getClass().getName(), " setContentView");
        //绑定ButterKnife注解框架
        ButterKnife.bind(this);

        // setColor(this, getResources().getColor(R.color.colorPrimaryDark));//用在setContentView之后
    }

    @Override
    protected void onStart () {
        super.onStart();

        LogUtils.i("" + this.getClass().getName(), " onStart");
    }

    @Override
    protected void onResume () {
        super.onResume();
        LogUtils.i("" + this.getClass().getName(), " onResume");
    }

    /*
     * Intent传递
     */
    protected void intent (Class<?> cls) {
        startActivity(new Intent(this, cls));
    }

    protected void intentForResult (Class<?> cls, Map<String, String> map, int requestCode) {
        Set<String> set = map.keySet();
        Intent intent = new Intent(this, cls);
        for (String key : set) {
            intent.putExtra(key, map.get(key));
        }
        startActivityForResult(intent, requestCode);
    }

    protected void intent (Class<?> cls, Map<String, String> map) {
        Set<String> set = map.keySet();
        Intent intent = new Intent(this, cls);
        for (String key : set) {
            intent.putExtra(key, map.get(key));
        }
        startActivity(intent);
    }

    protected void intentForResult (Class<?> cls, int requestCode) {
        Intent intent = new Intent(this, cls);
        startActivityForResult(intent, requestCode);
    }

    /*
     * startActivityForResult返回跳转
     */
    protected void intentSetResult (Map<String, String> map, int resultCode) {
        Set<String> set = map.keySet();
        Intent intent = new Intent();
        for (String key : set) {
            intent.putExtra(key, map.get(key));
        }
        setResult(resultCode, intent);
        finish();
    }


    @Override
    protected void onStop () {
        super.onStop();
        LogUtils.i("" + this.getClass().getName(), " onStop");
    }

    @Override
    protected void onPause () {
        super.onPause();
        LogUtils.i("" + this.getClass().getName(), " onPause");
    }

    @Override
    protected void onRestart () {
        super.onRestart();
        LogUtils.i("" + this.getClass().getName(), " onRestart");
    }

    @Override
    protected void onDestroy () {
        super.onDestroy();
        LogUtils.i("" + this.getClass().getName(), " onDestroy");

        //将Activity移除AppManager
        AppManager.getAppManager().removeActivity(this);
    }

    /**
     * 设置状态栏颜色
     *
     * @param activity 需要设置的activity
     * @param color    状态栏颜色值
     */
    public static void setColor (Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 设置状态栏透明
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 生成一个状态栏大小的矩形
            View statusView = createStatusView(activity, color);
            // 添加 statusView 到布局中
            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
            decorView.addView(statusView);
            // 设置根布局的参数
            ViewGroup rootView = (ViewGroup) ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
            rootView.setFitsSystemWindows(true);
            rootView.setClipToPadding(true);
        }
    }

    /**
     * 生成一个和状态栏大小相同的矩形条
     *
     * @param activity 需要设置的activity
     * @param color    状态栏颜色值
     * @return 状态栏矩形条
     */
    private static View createStatusView (Activity activity, int color) {
        // 获得状态栏高度
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        int statusBarHeight = activity.getResources().getDimensionPixelSize(resourceId);

        // 绘制一个和状态栏一样高的矩形
        View statusView = new View(activity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                statusBarHeight);
        statusView.setLayoutParams(params);
        statusView.setBackgroundColor(color);
        return statusView;
    }
}
