package com.engine.project1;

// FileHandle is an object used to locate a file. It acts as a pointing directory for your game to load. For loading in texture, audio, settings.

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class Utils {

    // Looks for any file name you put in, inside your jar resource folder.
    public static FileHandle getClasspath(String filepath) {
        return Gdx.files.classpath(filepath);
    }

    // Apps working directory.
    public static FileHandle getInternalPath(String filepath) {
        return Gdx.files.internal(filepath);
    }

    // Reading and writing.
    public static FileHandle getLocalPath(String filepath) {
        return Gdx.files.local(filepath);
    }

    // Storing and writing large files.
    public static FileHandle getExternalPath(String filepath) {
        return Gdx.files.external(filepath);
    }
}
