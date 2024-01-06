package com.smartelephantapps.minicraftandroid.utils;

import static android.opengl.GLES30.*;

import android.content.Context;
import android.util.Log;

public final class ShaderUtils {

    private static final String TAG = "ShaderUtils";

    private ShaderUtils() {}

    public static int load(Context context, int vertexResourceId, int fragmentResourceId) {
        String vert = FileUtils.loadAsStringFromResources(context, vertexResourceId);
        String frag = FileUtils.loadAsStringFromResources(context, fragmentResourceId);

        return create(vert, frag);
    }

    public static int create(String vert, String frag) {
        int program = glCreateProgram();

        int vertID = glCreateShader(GL_VERTEX_SHADER);
        int fragID = glCreateShader(GL_FRAGMENT_SHADER);

        glShaderSource(vertID, vert);
        glShaderSource(fragID, frag);

        final int[] compileStatus = new int[1];

        glCompileShader(vertID);
        glGetShaderiv(vertID, GL_COMPILE_STATUS, compileStatus, 0);
        if (compileStatus[0] == GL_FALSE) {
            Log.e(TAG, "Failed to compile vertex shader!\n" + glGetShaderInfoLog(vertID));
            return -1;
        }


        glCompileShader(fragID);
        glGetShaderiv(fragID, GL_COMPILE_STATUS, compileStatus, 0);
        if (compileStatus[0] == GL_FALSE) {
            Log.e(TAG, "Failed to compile fragment shader!\n" + glGetShaderInfoLog(fragID));
            return -1;
        }


        glAttachShader(program, vertID);
        glAttachShader(program, fragID);

        // TODO: Check Link & Validation Status
        glLinkProgram(program);
        glValidateProgram(program);

        glDeleteShader(vertID);
        glDeleteShader(fragID);

        return program;
    }
}
