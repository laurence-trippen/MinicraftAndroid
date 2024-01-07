package com.smartelephantapps.minicraftandroid.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public final class BitmapUtils {

    private BitmapUtils() {}

    public static Bitmap flipBitmap(Bitmap originalBitmap, boolean horizontal, boolean vertical) {
        Matrix matrix = new Matrix();

        // Flip horizontally
        if (horizontal) {
            matrix.setScale(-1, 1);
        }

        // Flip vertically
        if (vertical) {
            matrix.setScale(1, -1);
        }

        // Combine the transformations
        matrix.postTranslate(originalBitmap.getWidth(), 0);

        // Create a new Bitmap with the flipped image
        Bitmap flippedBitmap = Bitmap.createBitmap(originalBitmap, 0, 0,
                originalBitmap.getWidth(), originalBitmap.getHeight(), matrix, true);

        // Recycle the original Bitmap if needed
        if (originalBitmap != flippedBitmap) {
            originalBitmap.recycle();
        }

        return flippedBitmap;
    }
}
