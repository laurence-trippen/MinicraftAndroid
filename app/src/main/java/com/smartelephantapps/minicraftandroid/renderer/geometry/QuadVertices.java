package com.smartelephantapps.minicraftandroid.renderer.geometry;

public final class QuadVertices {

    private QuadVertices() {}

    public static float[] vertices = {
            -0.5f,  0.5f, 0.0f, // top left
            -0.5f, -0.5f, 0.0f, // bottom left
            0.5f, -0.5f, 0.0f,  // bottom right
            0.5f,  0.5f, 0.0f   // top right
    };

    public static byte[] indices = {
            0, 1, 2, 0, 2, 3
    };
}
