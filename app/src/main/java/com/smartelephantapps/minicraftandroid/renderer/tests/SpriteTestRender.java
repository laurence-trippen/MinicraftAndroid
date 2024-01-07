package com.smartelephantapps.minicraftandroid.renderer.tests;

import static android.opengl.GLES20.*;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.smartelephantapps.minicraftandroid.R;
import com.smartelephantapps.minicraftandroid.renderer.Sprite;
import com.smartelephantapps.minicraftandroid.renderer.Texture2D;

public class SpriteTestRender implements ITestRender {

    private static final String TAG = "SpriteTestRender";

    private final Context context;

    private Sprite minicraftSprite;

    public SpriteTestRender(Context context) {
        this.context = context;
    }

    @Override
    public void init() {
        try {
            Bitmap icon1Bitmap = Texture2D.loadFromResource(this.context, R.drawable.icons);

            this.minicraftSprite = new Sprite(context);
            this.minicraftSprite.load(icon1Bitmap);
            this.minicraftSprite.bind();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void render() {

        this.minicraftSprite.render();
        // this.minicraftSprite.unbind();
    }
}
