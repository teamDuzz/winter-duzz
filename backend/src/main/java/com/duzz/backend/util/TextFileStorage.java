package com.duzz.backend.util;

import lombok.RequiredArgsConstructor;

import java.io.File;
import java.nio.file.Files;

@RequiredArgsConstructor
public class TextFileStorage {
    private final String basePath = "assets/";

    private final String key;

    public String read() {
        try {
            var f = new File(basePath + key + ".txt");
            makeFileIfNotExists(f);

            return new String(Files.readAllBytes(f.toPath()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void write(String value) {
        try {
            var f = new File(basePath + key + ".txt");
            makeFileIfNotExists(f);

            Files.write(f.toPath(), value.getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void makeFileIfNotExists(File f) {
        if (!f.exists()) {
            f.getParentFile().mkdirs();
            try {
                f.createNewFile();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
