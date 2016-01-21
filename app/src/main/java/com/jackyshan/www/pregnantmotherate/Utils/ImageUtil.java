package com.jackyshan.www.pregnantmotherate.Utils;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Base64;

import com.jackyshan.www.pregnantmotherate.General.Config.LogUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Random;
import java.util.zip.GZIPOutputStream;

/**
 * Created by yaoshibang on 2014/12/25.
 */
public class ImageUtil {
    public static enum ScalingLogic {

        CROP,
        FIT
    }

    public static Bitmap getBitmap(String path) {

        return getBitmap(new File(path));
    }

    public static Bitmap getBitmap(File file) {

        try {

            if (!file.exists()) {
                return null;
            } else {

                Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
                return bitmap;

            }


        } catch (Exception ex) {
            LogUtil.LogErr(ImageUtil.class, ex);
        }
        return null;
    }

    public static Bitmap getOptBitmap(String path, int width, int height) {

        File file = new File(path);
        if (!file.exists()) {
            return null;
        }

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        int imgW = options.outWidth;
        int imgH = options.outHeight;

        int inSampleSize = 1;
        if (imgH > height || imgW > width) {
            // 计算出实际宽高和目标宽高的比率
            final int heightRatio = Math.round((float) imgH / (float) height);
            final int widthRatio = Math.round((float) imgW / (float) width);
            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
            // 一定都会大于等于目标的宽和高。
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }

        return getOptBitmap(path, inSampleSize);
    }

    public static Bitmap getOptBitmap(String path, int sampleSize) {

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = sampleSize;

        InputStream is = null;
        try {

            is = new FileInputStream(path);

            return BitmapFactory.decodeStream(is, null, options);


        } catch (Exception ex) {
            LogUtil.LogErr(ImageUtil.class, ex);
        } finally {
            try {
                is.close();
            } catch (Exception ex) {

            }
        }
        return null;

//        return BitmapFactory.decodeFile(path, options);
    }

    public static String encryptedPassword(String password){
        String rnd = GenerateRandom(9);

        return ImageUtil.passwordBase64(rnd.substring(0,2)
                + ImageUtil.passwordBase64(rnd.substring(2,5) + password + rnd.substring(5,9)));
    }
    private static String passwordBase64(String password){

        byte[] passwordByte =password.getBytes();
        String passwordString = new String(
                Base64.encode(passwordByte, Base64.DEFAULT));
        return passwordString;
    }

    private static final String GenerateRandom(int length)
    {
        Random randGen = new Random(System.currentTimeMillis());
        char[] numbersAndLetters = ("abcdefghijklmnopqrstuvwxyz" +
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();

        char [] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = numbersAndLetters[randGen.nextInt(52)];
        }
        return new String(randBuffer);
    }


    public static String compressAndBase64Bitmap(Bitmap bitmap) {

        try {
            if (bitmap == null)
                return null;

            ByteArrayOutputStream imageBaos = null;
            ByteArrayOutputStream baos = null;
//            GZIPOutputStream gzip = null;

            try {

                imageBaos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, imageBaos);
                byte[] imageBytes = imageBaos.toByteArray();
//
//                baos = new ByteArrayOutputStream();
//                gzip = new GZIPOutputStream(baos);
//                gzip.write(imageBytes);

//                byte[] imageCompressBytes = baos.toByteArray();

                String imageBase64String = new String(
                        Base64.encode(imageBytes, 0, imageBytes.length, Base64.DEFAULT), "UTF-8");

                return imageBase64String;

            } catch (Exception ex) {
                LogUtil.LogErr(ImageUtil.class, ex);

            } finally {
                try {
                    imageBaos.close();

                } catch (Exception e) {

                }
                try {
                    baos.close();

                } catch (Exception e) {

                }
//                try {
//                    gzip.close();
//
//                } catch (Exception e) {
//
//                }

            }

        } catch (Exception ex) {
            LogUtil.LogErr(ImageUtil.class, ex);
        }
        return null;

    }

    public static String compressAndBase64ImageFile(String imagePath) {

        return compressAndBase64ImageFile(new File(imagePath));

    }

    public static String compressAndBase64ImageFile(File imageFile) {

        try {

            if (imageFile == null)
                return null;
            if (!imageFile.exists())
                return null;

            ByteArrayOutputStream fileByteArrayOutputStream = null;
            FileInputStream fileInputStream = null;

            ByteArrayOutputStream baos = null;
            GZIPOutputStream gzip = null;

            try {

                fileByteArrayOutputStream = new ByteArrayOutputStream();
                fileInputStream = new FileInputStream(imageFile);

                int read;
                byte[] buff = new byte[4096];
                while ((read=fileInputStream.read(buff)) != -1) {

                    fileByteArrayOutputStream.write(buff, 0, read);

                }

                byte[] imageFileBytes = fileByteArrayOutputStream.toByteArray();

                baos = new ByteArrayOutputStream();
                gzip = new GZIPOutputStream(baos);
                gzip.write(imageFileBytes);

                byte[] imageCompressBytes = baos.toByteArray();

                String imageBase64String = new String(
                        Base64.encode(imageCompressBytes, 0, imageCompressBytes.length, Base64.DEFAULT), "UTF-8");

                return imageBase64String;


            } catch (Exception ex) {
                LogUtil.LogErr(ImageUtil.class, ex);
            } finally {
                try {
                    fileByteArrayOutputStream.close();
                } catch (Exception e) {
                }
                try {
                    fileInputStream.close();
                } catch (Exception e) {
                }
                try {
                    gzip.close();
                } catch (Exception e) {
                }
                try {
                    baos.close();
                } catch (Exception e) {
                }
            }



        } catch (Exception ex) {
            LogUtil.LogErr(ImageUtil.class, ex);
        }

        return null;

    }

    public static void saveImageToPath(Bitmap bitmap, String path) {

        try {

            if (bitmap == null || path == null)
                return;

            ByteArrayOutputStream baos = null;
            FileOutputStream fs = null;

            try {

                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);

                byte[] imageBytes = baos.toByteArray();

                File file = new File(path);
                if (file.isDirectory())
                    return;
                if (!file.exists()) {
                    File parentFile = file.getParentFile();
                    if (!parentFile.exists())
                        if (!parentFile.mkdirs())
                            return;
                    file.createNewFile();
                }
                fs = new FileOutputStream(path);

                fs.write(imageBytes);

            } catch (Exception ex) {
                LogUtil.LogErr(ImageUtil.class, ex);
            } finally {
                try {
                    baos.close();
                } catch (Exception e) {

                }
                try {
                    fs.close();
                } catch (Exception e) {

                }
            }

        } catch (Exception ex) {
            LogUtil.LogErr(ImageUtil.class, ex);
        }
    }


    public static Bitmap createScaledBitmap(Bitmap unscaledBitmap, int dstWidth, int dstHeight, ScalingLogic scalingLogic) {
        Rect srcRect = calculateSrcRect(unscaledBitmap.getWidth(), unscaledBitmap.getHeight(), dstWidth, dstHeight, scalingLogic);
        Rect dstRect = calculateDstRect(unscaledBitmap.getWidth(), unscaledBitmap.getHeight(), dstWidth, dstHeight, scalingLogic);
        Bitmap scaledBitmap = Bitmap.createBitmap(dstRect.width(), dstRect.height(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(scaledBitmap);
        canvas.drawBitmap(unscaledBitmap, srcRect, dstRect, new Paint(Paint.FILTER_BITMAP_FLAG));return scaledBitmap;
    }

    public static Rect calculateSrcRect(int srcWidth, int srcHeight, int dstWidth, int dstHeight, ScalingLogic scalingLogic) {
        if (scalingLogic == ScalingLogic.CROP) {
            final float srcAspect = (float)srcWidth / (float)srcHeight;
            final float dstAspect = (float)dstWidth / (float)dstHeight;
            if (srcAspect > dstAspect) {
                final int srcRectWidth = (int)(srcHeight * dstAspect);
                final int srcRectLeft = (srcWidth - srcRectWidth) / 2;
                return new Rect(srcRectLeft, 0, srcRectLeft + srcRectWidth, srcHeight);
            } else {
                final int srcRectHeight = (int)(srcWidth / dstAspect);
                final int scrRectTop = (int)(srcHeight - srcRectHeight) / 2;
                return new Rect(0, scrRectTop, srcWidth, scrRectTop + srcRectHeight);
            }
        } else {
            return new Rect(0, 0, srcWidth, srcHeight);
        }
    }
    public static Rect calculateDstRect(int srcWidth, int srcHeight, int dstWidth, int dstHeight, ScalingLogic scalingLogic) {
        if (scalingLogic == ScalingLogic.FIT) {
            final float srcAspect = (float)srcWidth / (float)srcHeight;
            final float dstAspect = (float)dstWidth / (float)dstHeight;
            if (srcAspect > dstAspect) {
                return new Rect(0, 0, dstWidth, (int)(dstWidth / srcAspect));
            } else {
                return new Rect(0, 0, (int)(dstHeight * srcAspect), dstHeight);
            }
        } else {
            return new Rect(0, 0, dstWidth, dstHeight);
        }
    }

    public static Bitmap decodeFile(String pathName, int dstWidth, int dstHeight, ScalingLogic scalingLogic) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathName, options);
        options.inJustDecodeBounds = false;
        options.inSampleSize = calculateSampleSize(options.outWidth, options.outHeight, dstWidth, dstHeight, scalingLogic);
        Bitmap unscaledBitmap = BitmapFactory.decodeFile(pathName, options);
        return unscaledBitmap;
    }
    public static int calculateSampleSize(int srcWidth, int srcHeight, int dstWidth, int dstHeight, ScalingLogic scalingLogic) {
        if (scalingLogic == ScalingLogic.FIT) {
            final float srcAspect = (float)srcWidth / (float)srcHeight;
            final float dstAspect = (float)dstWidth / (float)dstHeight;
            if (srcAspect > dstAspect) {
                return srcWidth / dstWidth;
            } else {
                return srcHeight / dstHeight;
            }
        } else {
            final float srcAspect = (float)srcWidth / (float)srcHeight;
            final float dstAspect = (float)dstWidth / (float)dstHeight;
            if (srcAspect > dstAspect) {
                return srcHeight / dstHeight;
            } else {
                return srcWidth / dstWidth;
            }
        }
    }
}
