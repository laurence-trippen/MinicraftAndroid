package com.smartelephantapps.minicraftandroid.renderer.gles2;

import static android.opengl.GLES20.GL_ARRAY_BUFFER;
import static android.opengl.GLES20.GL_ELEMENT_ARRAY_BUFFER;
import static android.opengl.GLES20.GL_FLOAT;
import static android.opengl.GLES20.GL_STATIC_DRAW;
import static android.opengl.GLES20.GL_TRIANGLES;
import static android.opengl.GLES20.GL_UNSIGNED_BYTE;
import static android.opengl.GLES20.glBindBuffer;
import static android.opengl.GLES20.glBufferData;
import static android.opengl.GLES20.glDisableVertexAttribArray;
import static android.opengl.GLES20.glDrawElements;
import static android.opengl.GLES20.glEnableVertexAttribArray;
import static android.opengl.GLES20.glGenBuffers;
import static android.opengl.GLES20.glVertexAttribPointer;

import com.smartelephantapps.minicraftandroid.core.IBindable;
import com.smartelephantapps.minicraftandroid.core.IRenderable;
import com.smartelephantapps.minicraftandroid.utils.BufferUtils;

public class StaticIndexedMesh implements IBindable, IRenderable {

    public final static int POSITION_ATTRIBUTE = 0;

    private final int verticesCount;

    private final int indicesCount;

    private int[] vboID = new int[1];

    private int[] iboID = new int[1];

    public StaticIndexedMesh(float[] vertices, byte[] indices) {
        this.verticesCount = vertices.length / 3;
        this.indicesCount = indices.length;

        int sizeOfBytes = vertices.length * 4; // 4 Bytes for 32bit float

        glGenBuffers(1, this.vboID, 0);
        glBindBuffer(GL_ARRAY_BUFFER, this.vboID[0]);
        glBufferData(GL_ARRAY_BUFFER, sizeOfBytes, BufferUtils.createFloatBuffer(vertices), GL_STATIC_DRAW);

        // Unbind VBO
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        glGenBuffers(1, this.iboID, 0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, this.iboID[0]);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, indices.length, BufferUtils.createByteBuffer(indices), GL_STATIC_DRAW);

        // Unbind IBO
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    @Override
    public void bind() {
        glBindBuffer(GL_ARRAY_BUFFER, this.vboID[0]);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, this.iboID[0]);
    }

    @Override
    public void unbind() {
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    }

    @Override
    public void render() {
        glEnableVertexAttribArray(POSITION_ATTRIBUTE);
        glVertexAttribPointer(POSITION_ATTRIBUTE, 3, GL_FLOAT, false, 3 * 4, 0);

        glDrawElements(GL_TRIANGLES, this.indicesCount, GL_UNSIGNED_BYTE, 0);

        glDisableVertexAttribArray(POSITION_ATTRIBUTE);
    }
}
