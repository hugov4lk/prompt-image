package com.wokecustoms.prompt.image;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class AbstractTest {

    public static String readFileToString(String fileName) {
        return new String(readFileBytes(fileName), StandardCharsets.UTF_8);
    }

    private static byte[] readFileBytes(String fileName) {
        try {
            ClassLoader classLoader = AbstractTest.class.getClassLoader();
            URL resource = classLoader.getResource(fileName);
            assertNotNull(resource, "File not found: " + fileName);
            return Files.readAllBytes(Paths.get(resource.toURI()));
        } catch (Exception e) {
            throw new RuntimeException("Exception: " + e.getMessage(), e);
        }
    }
}
