package com.smartelephantapps.minicraftandroid.game;

import android.content.Context;

public class MinicraftGame {

    private long lastTime = System.nanoTime();
    private double unprocessed = 0;
    private double nsPerTick = 1000000000.0 / 60;
    private int frames = 0;
    private int ticks = 0;
    private long lastTimer1 = System.currentTimeMillis();

    public MinicraftGame(Context context) {

    }

    public void init() {

    }

    public void tick() {

    }
}
