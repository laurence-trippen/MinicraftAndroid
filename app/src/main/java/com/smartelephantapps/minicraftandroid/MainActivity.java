package com.smartelephantapps.minicraftandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.opengl.GLSurfaceView;
import android.os.Bundle;

import com.smartelephantapps.minicraftandroid.game.MinicraftGame;
import com.smartelephantapps.minicraftandroid.gles.MinicraftGLSurfaceView;

public class MainActivity extends AppCompatActivity {

    private GLSurfaceView glView;

    private MinicraftGame game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.game = new MinicraftGame(this);

        this.glView = new MinicraftGLSurfaceView(this, game);
        setContentView(this.glView);
    }
}
