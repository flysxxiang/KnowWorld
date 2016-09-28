package knowworld.com.zx.konwworld.utils.imgaeloader;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import knowworld.com.zx.konwworld.R;
import knowworld.com.zx.konwworld.utils.GlideCircleTransform;

/**
 * @author fly_xiang_mac
 * @description 图片加载类
 * @time 2016-01-12
 */

public class ImageLoader implements ImageLoaderWrapper {

    @Override
    public void displayImage(ImageView imageView, String url, Context context) {

        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.ic_loading_rotate)// 加载中显示的图片
                .crossFade()
                .error(R.drawable.error)// 加载失败时显示的图片
                .into(imageView);
    }

    @Override
    public void displayCicleImage(ImageView imageView, String url, Context context) {

        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.ic_loading_rotate)// 加载中显示的图片
                .crossFade()
                .error(R.drawable.error)// 加载失败时显示的图片
                .transform(new GlideCircleTransform(context))
                .into(imageView);
    }


}
