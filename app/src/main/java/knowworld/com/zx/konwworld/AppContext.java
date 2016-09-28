package knowworld.com.zx.konwworld;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * @author fly_xiang_mac
 * @description
 * @time 2016-09-21
 */

public class AppContext extends Application {
    private static AppContext mContext;

    @Override
    public void onCreate () {
        super.onCreate();
        mContext = this;

        LeakCanary.install(this);

        // 输出详细的Bugly SDK的Log, 每一条Crash都会被立即上报, 自定义日志将会在Logcat中输出
        // 发布改为false
        CrashReport.initCrashReport(getApplicationContext(), "900054509", AppConfig.isBugly);
    }
}
