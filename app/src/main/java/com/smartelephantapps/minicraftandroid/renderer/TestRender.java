package com.smartelephantapps.minicraftandroid.renderer;

import static android.opengl.GLES20.*;

import android.content.Context;

import com.smartelephantapps.minicraftandroid.R;
import com.smartelephantapps.minicraftandroid.utils.BufferUtils;

public class TestRender {

    private final Context context;

    private int[] vboID = new int[1];

    private Shader baseColorShader;

    private int positionAttribLocation;
    private int colorUniformLocation;

    private float color[] = { 0.63671875f, 0.76953125f, 0.22265625f, 1.0f };

    public TestRender(Context context) {
        this.context = context;
    }

    public void init() {
        // Winding: CCW
        float[] vertices = new float[] {
                -0.5f, -0.5f, 0.0f, // Left-Bottom
                0.5f, -0.5f, 0.0f,  // Right-Bottom
                0.0f,  0.5f, 0.0f   // Top-center
        };

        int sizeOfBytes = vertices.length * 4; // 4 Bytes for 32bit float

        glGenBuffers(1, this.vboID, 0);
        glBindBuffer(GL_ARRAY_BUFFER, this.vboID[0]);
        glBufferData(GL_ARRAY_BUFFER, sizeOfBytes, BufferUtils.createFloatBuffer(vertices), GL_STATIC_DRAW);
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        this.baseColorShader = new Shader(context, R.raw.test_render_vs, R.raw.test_render_fs);

        this.baseColorShader.enable();
        this.positionAttribLocation = glGetAttribLocation(this.baseColorShader.getID(), "vPosition");
        this.colorUniformLocation = glGetUniformLocation(this.baseColorShader.getID(), "vColor");
        this.baseColorShader.disable();
    }

    public void render() {
        this.baseColorShader.enable();

        glBindBuffer(GL_ARRAY_BUFFER, this.vboID[0]);

        glEnableVertexAttribArray(this.positionAttribLocation);
        glVertexAttribPointer(this.positionAttribLocation, 3, GL_FLOAT, false, 3 * 4, 0);



        glUniform4fv(colorUniformLocation, 1, color, 0);

        glDrawArrays(GL_TRIANGLES, 0,  9 / 3);

        this.baseColorShader.disable();

        glDisableVertexAttribArray(positionAttribLocation);
    }
}
