package knowworld.com.zx.konwworld.utils.imgaeloader;

/**
 * @author fly_xiang_mac
 * @description 获取图片加载器
 * @time 2016-01-12
 */

public class ImageLoaderFactory {
    private static ImageLoaderWrapper sInstance;

    private ImageLoaderFactory() {

    }

    public static ImageLoaderWrapper getLoader() {
        if (sInstance == null) {
            synchronized (ImageLoaderFactory.class) {
                if (sInstance == null) {
                    sInstance = new ImageLoader();
                }
            }
        }
        return sInstance;
    }
}
