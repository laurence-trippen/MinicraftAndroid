package com.smartelephantapps.minicraftandroid.renderer.geometry;

public final class SpriteVertices {

    private SpriteVertices() {}

    public static float[] vertices = {
            // x  y   z   s   t
            -1f,  1f, 0f, 0f, 1f, // top left
            -1f, -1f, 0f, 0f, 0f, // bottom left
             1f, -1f, 0f, 1f, 0f, // bottom right
             1f,  1f, 0f, 1f, 1f  // top right
    };

    public static byte[] indices = {
            0, 1, 2, 0, 2, 3
    };
}
