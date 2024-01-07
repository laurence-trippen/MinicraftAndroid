package com.smartelephantapps.minicraftandroid.renderer;

import static android.opengl.GLES20.*;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import com.smartelephantapps.minicraftandroid.core.IBindable;

public class Texture2D implements IBindable {

    private int textureID[] = new int[1];

    private int textureSlot = GL_TEXTURE0;

    public void load(Bitmap bitmap) throws Exception {
        glGenTextures(1, textureID, 0);

        if (textureID[0] == 0) {
            throw new Exception("Allocating GL Texture failed!");
        }

        glActiveTexture(this.textureSlot);
        glBindTexture(GL_TEXTURE_2D, textureID[0]);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        // Load bitmap into GPU memory
        GLUtils.texImage2D(GL_TEXTURE_2D, 0, GL_RGBA, bitmap, 0);

        // Unbind texture
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    @Override
    public void bind() {
        glActiveTexture(this.textureSlot);
        glBindTexture(GL_TEXTURE_2D, textureID[0]);
    }

    @Override
    public void unbind() {
        glActiveTexture(this.textureSlot);
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public void setTextureSlot(int slot) {
        this.textureSlot = slot;
    }

    public static Bitmap loadFromResource(Context context, int resourceId) throws Exception {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resourceId);

        if (bitmap == null) {
            throw new Exception("Bitmap couldn't loaded from Resources");
        }

        return bitmap;
    }
}
