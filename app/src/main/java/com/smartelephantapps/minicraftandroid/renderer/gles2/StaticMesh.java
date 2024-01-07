package com.smartelephantapps.minicraftandroid.renderer.gles2;

import static android.opengl.GLES20.GL_ARRAY_BUFFER;
import static android.opengl.GLES20.GL_FLOAT;
import static android.opengl.GLES20.GL_STATIC_DRAW;
import static android.opengl.GLES20.GL_TRIANGLES;
import static android.opengl.GLES20.glBindBuffer;
import static android.opengl.GLES20.glBufferData;
import static android.opengl.GLES20.glDisableVertexAttribArray;
import static android.opengl.GLES20.glDrawArrays;
import static android.opengl.GLES20.glEnableVertexAttribArray;
import static android.opengl.GLES20.glGenBuffers;
import static android.opengl.GLES20.glVertexAttribPointer;

import com.smartelephantapps.minicraftandroid.core.IBindable;
import com.smartelephantapps.minicraftandroid.core.IRenderable;
import com.smartelephantapps.minicraftandroid.utils.BufferUtils;

public class StaticMesh implements IBindable, IRenderable {

    public final static int POSITION_ATTRIBUTE = 0;

    private final int verticesCount;

    private int[] vboID = new int[1];

    public StaticMesh(float[] vertices) {
        this.verticesCount = vertices.length / 3;

        int sizeOfBytes = vertices.length * 4; // 4 Bytes for 32bit float

        glGenBuffers(1, this.vboID, 0);
        glBindBuffer(GL_ARRAY_BUFFER, this.vboID[0]);
        glBufferData(GL_ARRAY_BUFFER, sizeOfBytes, BufferUtils.createFloatBuffer(vertices), GL_STATIC_DRAW);

        // Unbind VBO
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    @Override
    public void bind() {
        glBindBuffer(GL_ARRAY_BUFFER, this.vboID[0]);
    }

    @Override
    public void unbind() {
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    @Override
    public void render() {
        glEnableVertexAttribArray(POSITION_ATTRIBUTE);
        glVertexAttribPointer(POSITION_ATTRIBUTE, 3, GL_FLOAT, false, 3 * 4, 0);

        glDrawArrays(GL_TRIANGLES, 0, this.verticesCount);

        glDisableVertexAttribArray(POSITION_ATTRIBUTE);
    }
}
