package com.smartelephantapps.minicraftandroid.renderer;

import static android.opengl.GLES20.*;

import android.content.Context;

import com.smartelephantapps.minicraftandroid.utils.ShaderUtils;

import java.util.HashMap;
import java.util.Map;

public class Shader {

    private boolean enabled = false;

    private final int ID;
    private Map<String, Integer> locationCache = new HashMap<>();

    public Shader(Context context, int vertexResourceId, int fragmentResourceId) {
        this.ID = ShaderUtils.load(context, vertexResourceId, fragmentResourceId);
    }

    public int getUniform(String name) {
        if (locationCache.containsKey(name))
            return locationCache.get(name);

        int result = glGetUniformLocation(ID, name);
        if (result == -1)
            System.err.println("Could not find uniform variable '" + name + "'!");
        else
            locationCache.put(name, result);
        return result;
    }

    public void setUniform1i(String name, int value) {
        if (!enabled) enable();
        glUniform1i(getUniform(name), value);
    }

    public void setUniform1f(String name, float value) {
        if (!enabled) enable();
        glUniform1f(getUniform(name), value);
    }

    public void setUniform2f(String name, float x, float y) {
        if (!enabled) enable();
        glUniform2f(getUniform(name), x, y);
    }

    // TODO: Implement Math Library like JOML, Android or own one.
    /*
    public void setUniform3f(String name, Vector3f vector) {
        if (!enabled) enable();
        glUniform3f(getUniform(name), vector.x, vector.y, vector.z);
    }

    public void setUniformMat4f(String name, Matrix4f matrix) {
        if (!enabled) enable();

        int count = matrix.elements.length;

        // TODO: Check if count is correct
        glUniformMatrix4fv(getUniform(name), 1, false, matrix.toFloatBuffer());

        // glUniformMatrix4(getUniform(name), false, matrix.toFloatBuffer());
    }
    */

    public void enable() {
        glUseProgram(ID);
        enabled = true;
    }

    public void disable() {
        glUseProgram(0);
        enabled = false;
    }

    public int getID() {
        return this.ID;
    }
}
