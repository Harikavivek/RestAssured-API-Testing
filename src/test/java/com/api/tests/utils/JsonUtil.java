package com.api.tests.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonUtil {
    public static String readJson(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}
