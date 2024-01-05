package com.smartelephantapps.minicraftandroid.gles;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.view.View;

public class MinicraftGLSurfaceView extends GLSurfaceView implements View.OnTouchListener {

    private final MinicraftGLRenderer renderer;

    public MinicraftGLSurfaceView(Context context) {
        super(context);

        // Create an OpenGL ES 2.0 context
        setEGLContextClientVersion(2);

        this.renderer = new MinicraftGLRenderer(context);
        this.setRenderer(this.renderer);

        // Set Renderer mode
        this.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);

        // Register Input Listener
        // setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // Input.x = event.getX();
        // Input.y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // Input.setState(Input.TOUCH_DOWN, true);
                break;
            case MotionEvent.ACTION_MOVE:
                // Input.setState(Input.TOUCH_MOVE, true);
                break;
            case MotionEvent.ACTION_UP:
                // Input.setState(Input.TOUCH_UP, true);
                break;
        }

        return true; // Return true to indicate that the event has been consume
    }
}
