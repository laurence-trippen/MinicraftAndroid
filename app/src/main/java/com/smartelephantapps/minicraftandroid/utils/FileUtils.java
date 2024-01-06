package com.smartelephantapps.minicraftandroid.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class FileUtils {

    private FileUtils() {}

    public static String loadAsStringFromResources(Context context, int resourceId) {
        final StringBuilder sb = new StringBuilder();

        InputStream inputStream = null;
        InputStreamReader reader = null;
        BufferedReader bufferedReader = null;

        try {
            inputStream = context.getResources().openRawResource(resourceId);
            reader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(reader);

            String nextLine = null;
            while ((nextLine = bufferedReader.readLine()) != null) {
                sb.append(nextLine).append('\n');
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeStream(bufferedReader);
            closeStream(reader);
            closeStream(inputStream);
        }

        return sb.toString();
    }

    private static void closeStream(Closeable closeable) {
        if (closeable == null) return;

        try {
            closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
