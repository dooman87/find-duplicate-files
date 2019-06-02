package com.dpokidov.reducer;

import com.dpokidov.model.FileInfo;
import com.dpokidov.loader.Loader;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Reduces input files based on the content.
 * Files that are the same will be grouped together.
 */
public class DuplicateReducer implements Reducer {
    private Loader loader;
    private Map<Integer, List<String>> byContentLength = new HashMap<>();

    public DuplicateReducer(Loader loader) {
        if (loader == null) {
            throw new IllegalArgumentException("loader is required");
        }

        this.loader = loader;
    }

    @Override
    public void add(FileInfo fileInfo) {
        if (fileInfo == null) {
            return;
        }

        int contentLength = fileInfo.getContent().length;

        if (byContentLength.containsKey(contentLength)) {
            byContentLength.get(contentLength).add(fileInfo.getPath());
        } else {
            byContentLength.put(contentLength, new ArrayList<>(Collections.singletonList(fileInfo.getPath())));
        }
    }

    @Override
    public Collection<List<String>> getResult() {
        Collection<List<String>> duplicates = new ArrayList<>();
        byContentLength
            .values()
            .stream()
            .filter(files -> files.size() > 1)
            .forEach(files -> findDuplicatesByContent(files).collect(Collectors.toCollection(() -> duplicates)));

        return duplicates;
    }

    private Stream<List<String>> findDuplicatesByContent(List<String> files) {
        Map<ByteBuffer, List<String>> byContent = new HashMap<>();
        files.forEach(f -> {
            try {
                FileInfo file = loader.loadFile(f);
                ByteBuffer buffer = ByteBuffer.wrap(file.getContent());
                if (byContent.containsKey(buffer)) {
                    byContent.get(buffer).add(file.getPath());
                } else {
                    byContent.put(buffer, new ArrayList<>(Collections.singletonList(file.getPath())));
                }
            } catch (IOException e) {
                System.err.printf("could not read file %s: %s", f, e.getMessage());
                e.printStackTrace(System.err);
            }
        });

        return byContent
                .values()
                .stream()
                .filter(byContentFiles -> byContentFiles.size() > 1);
    }
}
