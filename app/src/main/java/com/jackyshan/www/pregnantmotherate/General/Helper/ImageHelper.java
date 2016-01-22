package com.jackyshan.www.pregnantmotherate.General.Helper;

import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by jackyshan on 1/22/16.
 */
public class ImageHelper {

    /**
     * 从AssetsRecipes食谱文件夹异步加载本地图片
     *
     * @param imageName
     * @param imageView
     */
    public  static void displayFromAssetsRecipe(String imageName, ImageView imageView) {
        // String imageUri = "res://mipmap-hdpi/recipes/image.png"; // from res
        ImageLoader.getInstance().displayImage("assets://recipes/" + imageName,
                imageView);
    }

    /**
     * 从网络异步下载图片
     *
     * @param uri
     * @param imageView
     */
    public static void displayFromWeb(String uri, ImageView imageView) {
        //"http://site.com/image.png" // from Web
        ImageLoader.getInstance().displayImage(uri, imageView);
    }

    /**
     * 从内存卡中异步加载本地图片
     *
     * @param uri
     * @param imageView
     */
    public static void displayFromSDCard(String uri, ImageView imageView) {
        // String imageUri = "file:///mnt/sdcard/image.png"; // from SD card
        ImageLoader.getInstance().displayImage("file://" + uri, imageView);
    }

    /**
     * 从assets文件夹中异步加载图片
     *
     * @param imageName
     * 图片名称，带后缀的，例如：1.png
     * @param imageView
     */
    public static void dispalyFromAssets(String imageName, ImageView imageView) {
        // String imageUri = "assets://image.png"; // from assets
        ImageLoader.getInstance().displayImage("assets://" + imageName,
                imageView);
    }

    /**
     * 从drawable中异步加载本地图片
     *
     * @param imageId
     * @param imageView
     */
    public static void displayFromDrawable(int imageId, ImageView imageView) {
        // String imageUri = "drawable://" + R.drawable.image; // from drawables
        // (only images, non-9patch)
        ImageLoader.getInstance().displayImage("drawable://" + imageId,
                imageView);
    }

    /**
     * 从内容提提供者中抓取图片
     */
    public static void displayFromContent(String uri, ImageView imageView) {
        // String imageUri = "content://media/external/audio/albumart/13"; //
        // from content provider
        ImageLoader.getInstance().displayImage("content://" + uri, imageView);
    }

}