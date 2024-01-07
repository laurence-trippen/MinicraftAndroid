package com.smartelephantapps.minicraftandroid.renderer;

import static android.opengl.GLES20.*;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.smartelephantapps.minicraftandroid.R;
import com.smartelephantapps.minicraftandroid.core.IBindable;
import com.smartelephantapps.minicraftandroid.core.IRenderable;
import com.smartelephantapps.minicraftandroid.renderer.geometry.SpriteVertices;
import com.smartelephantapps.minicraftandroid.utils.BufferUtils;

public class Sprite implements IBindable, IRenderable {

    private static final String TAG = "Sprite";

    private static final int POSITION_ATTRIBUTE = 0;

    private static final int UV_ATTRIBUTE = 1;

    private Texture2D spriteTexture;

    private Shader spriteShader;

    private int[] vboID = new int[1];

    private int[] iboID = new int[1];

    public Sprite(Context context) {
        this.spriteTexture = new Texture2D();

        this.spriteShader = new Shader(context, R.raw.sprite_vs, R.raw.sprite_fs);

        int sizeOfBytes = SpriteVertices.vertices.length * 4; // 4 byte 32bit float

        glGenBuffers(1, this.vboID, 0);
        glBindBuffer(GL_ARRAY_BUFFER, this.vboID[0]);
        glBufferData(
                GL_ARRAY_BUFFER,
                sizeOfBytes,
                BufferUtils.createFloatBuffer(SpriteVertices.vertices),
                GL_STATIC_DRAW);

        glBindBuffer(GL_ARRAY_BUFFER, 0);

        glGenBuffers(1, this.iboID, 0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, this.iboID[0]);
        glBufferData(
                GL_ELEMENT_ARRAY_BUFFER,
                SpriteVertices.indices.length,
                BufferUtils.createByteBuffer(SpriteVertices.indices),
                GL_STATIC_DRAW);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    public void load(Bitmap bitmap) {
        try {
            this.spriteTexture.load(bitmap);
        } catch (Exception e) {
            Log.e(TAG, "FAILED TO LOAD SPRITE!");
            e.printStackTrace();
        }
    }

    @Override
    public void bind() {
        this.spriteTexture.bind();
        this.spriteShader.enable();

        glBindBuffer(GL_ARRAY_BUFFER, this.vboID[0]);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, this.iboID[0]);
    }

    @Override
    public void unbind() {
        this.spriteShader.disable();
        this.spriteTexture.unbind();

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    @Override
    public void render() {
        glEnableVertexAttribArray(POSITION_ATTRIBUTE);
        glVertexAttribPointer(POSITION_ATTRIBUTE, 3, GL_FLOAT, false, 5 * 4, 0);

        glEnableVertexAttribArray(UV_ATTRIBUTE);
        glVertexAttribPointer(UV_ATTRIBUTE, 2, GL_FLOAT, false, 5 * 4, 3 * 4);

        glDrawElements(GL_TRIANGLES, SpriteVertices.indices.length, GL_UNSIGNED_BYTE, 0);

        glDisableVertexAttribArray(UV_ATTRIBUTE);
        glDisableVertexAttribArray(POSITION_ATTRIBUTE);
    }
}
