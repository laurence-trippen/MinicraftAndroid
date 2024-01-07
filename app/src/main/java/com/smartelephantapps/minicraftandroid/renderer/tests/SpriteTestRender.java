package com.smartelephantapps.minicraftandroid.renderer.tests;

import static android.opengl.GLES20.*;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.smartelephantapps.minicraftandroid.R;
import com.smartelephantapps.minicraftandroid.renderer.Shader;
import com.smartelephantapps.minicraftandroid.renderer.Texture2D;
import com.smartelephantapps.minicraftandroid.renderer.geometry.QuadVertices;
import com.smartelephantapps.minicraftandroid.renderer.gles2.StaticIndexedMesh;
import com.smartelephantapps.minicraftandroid.renderer.gles2.StaticMesh;

public class SpriteTestRender implements ITestRender {

    private static final String TAG = "SpriteTestRender";

    private final Context context;

    private Texture2D icon1Texture;

    private Shader baseColorShader;

    private StaticMesh triangleMesh;

    private StaticIndexedMesh quadMesh;

    private int colorUniformLocation;

    private float color[] = { 0.93671875f, 0.76953125f, 0.52265625f, 1.0f };

    public SpriteTestRender(Context context) {
        this.context = context;
    }

    @Override
    public void init() {
        try {
            this.baseColorShader = new Shader(context, R.raw.base_color_vs, R.raw.base_color_fs);
            this.baseColorShader.enable();
            this.colorUniformLocation = glGetUniformLocation(this.baseColorShader.getID(), "vColor");
            this.baseColorShader.disable();


            Bitmap icon1Bitmap = Texture2D.loadFromResource(this.context, R.drawable.icons);

            this.icon1Texture = new Texture2D();
            this.icon1Texture.load(icon1Bitmap);
            this.icon1Texture.bind();


            // Winding: CCW
            float[] vertices = new float[] {
                    -0.5f, -0.5f, 0.0f, // Left-Bottom
                    0.5f, -0.5f, 0.0f,  // Right-Bottom
                    0.0f,  0.5f, 0.0f   // Top-center
            };

            this.triangleMesh = new StaticMesh(vertices);

            this.quadMesh = new StaticIndexedMesh(QuadVertices.vertices, QuadVertices.indices);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void render() {
        this.baseColorShader.enable();

        glUniform4fv(colorUniformLocation, 1, color, 0);

        // this.triangleMesh.bind();
        // this.triangleMesh.render();
        // this.triangleMesh.unbind();

        this.quadMesh.bind();
        this.quadMesh.render();
        this.quadMesh.unbind();

        this.baseColorShader.disable();
    }
}
