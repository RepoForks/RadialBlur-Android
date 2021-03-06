package com.dk.image.process.radialblur;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

/**
 * Created by DK on 2015/9/1.
 */
public class RadialBlur {
    /**
     * use for 2D-Radial-Blur
     *
     * @param src
     * @param dx
     * @param dy
     * @return
     */
    public static Bitmap doRadialBlur(Bitmap src, int dx, int dy) {
        return doRadialBlur(src, dx, dy, 20);
    }

    /**
     * use for 2D-Radial-Blur
     *
     * @param src
     * @param dx    It effetcs the speed. you can use 1/-1 for a test
     * @param dy    It effetcs the speed. you can use 1/-1 for a test
     * @param times effects motion length
     * @return
     */
    public static Bitmap doRadialBlur(Bitmap src, int dx, int dy, int times) {

        Bitmap dst = Bitmap.createBitmap(src.getWidth(), src.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(dst);
        Matrix matrix = new Matrix();
        Paint paint = new Paint();
        canvas.drawBitmap(src, matrix, paint);

        paint.setAlpha(51); // 51 / 255 =20%

        for (int i = 0; i < times; i++) {
            matrix.setTranslate(i * dx, i * dy);
            canvas.drawBitmap(src, matrix, paint);
        }

        return dst;
    }

    public static Bitmap doRadialBlur(Bitmap src, int centerX, int centerY, float factor) {
        return doRadialBlur(src, centerX, centerY, factor, 20);
    }

    /**
     * use for Z-Radial-Blur
     *
     * @param src
     * @param centerX
     * @param centerY
     * @param factor  It effetcs the speed. you can use 0.01 for a test
     * @param times   effect motion length
     * @return
     */
    public static Bitmap doRadialBlur(Bitmap src, int centerX, int centerY, float factor, int times) {

        Bitmap dst = Bitmap.createBitmap(src.getWidth(), src.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(dst);
        Matrix matrix = new Matrix();
        Paint paint = new Paint();
        canvas.drawBitmap(src, matrix, paint);
        paint.setAlpha(51);

        for (int i = 0; i < times; i++) {
            matrix.setScale(i * factor + 1, i * factor + 1, centerX, centerY);
            canvas.drawBitmap(src, matrix, paint);
        }
        return dst;
    }
}
