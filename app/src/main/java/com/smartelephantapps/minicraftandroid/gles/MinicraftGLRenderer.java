package com.smartelephantapps.minicraftandroid.gles;

import static android.opengl.GLES20.*;

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.smartelephantapps.minicraftandroid.game.MinicraftGame;
import com.smartelephantapps.minicraftandroid.renderer.tests.ITestRender;
import com.smartelephantapps.minicraftandroid.renderer.tests.SpriteTestRender;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MinicraftGLRenderer implements GLSurfaceView.Renderer {

    private final Context context;

    private final MinicraftGame game;

    private final ITestRender testRender;

    public MinicraftGLRenderer(Context context, MinicraftGame game) {
        this.context = context;
        this.game = game;

        this.testRender = new SpriteTestRender(context);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        this.game.init();

        this.testRender.init();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        glClear(GL_COLOR_BUFFER_BIT);

        // Render here ...

        this.game.tick();

        this.testRender.render();
    }
}
