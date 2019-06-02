package com.dpokidov.loader;

import com.dpokidov.model.FileInfo;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.function.Function;

/**
 * Loads files from the local filesystem.
 */
public class LocalFileLoader implements Loader {
    @Override
    public void withFiles(String uri, Function<FileInfo, Void> callback) throws IOException {
        if (uri == null || callback == null) {
            throw new IllegalArgumentException("input uri and callback are required");
        }

        String trimmedUri = uri.trim();
        if (trimmedUri.length() == 0) {
            throw new IllegalArgumentException("input uri can not be empty");
        }

        Path inputDir = Paths.get(trimmedUri).toAbsolutePath();

        if (!Files.exists(inputDir)) {
            throw new IllegalArgumentException("directory " + uri + " does not exist");
        }

        if (!Files.isDirectory(inputDir)) {
            throw new IllegalArgumentException("expected directory, but got regular file");
        }

        Files.walkFileTree(inputDir, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                callback.apply(new FileInfo(file.toAbsolutePath().toString(), Files.readAllBytes(file)));

                return FileVisitResult.CONTINUE;
            }
        });
    }

    @Override
    public FileInfo loadFile(String uri) throws IOException {
        return new FileInfo(uri, Files.readAllBytes(Paths.get(uri)));
    }
}
