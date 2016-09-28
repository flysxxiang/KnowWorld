package knowworld.com.zx.konwworld.utils.imgaeloader;

import android.content.Context;
import android.widget.ImageView;

/**
 * @author fly_xiang_mac
 * @description 抽象图片加载的一些方法
 * @time 2016-01-12
 */

public interface ImageLoaderWrapper {
    /**
     * 展示图片
     *
     * @param imageView
     * @param url
     * @param context
     */
    public void displayImage(ImageView imageView, String url, Context context);

    /**
     * 显示圆角图片
     *
     * @param imageView
     * @param url
     * @param context
     */
    public void displayCicleImage(ImageView imageView, String url, Context context);
}
