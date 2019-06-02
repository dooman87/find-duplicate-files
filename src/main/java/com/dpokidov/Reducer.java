package com.dpokidov;

import java.util.List;

/**
 * Reducers accepts files on input and builds
 * list of duplicates files
 */
public interface Reducer {
    /**
     * Adds a file to process by reducer.
     * @param fileInfo file to add
     */
    void add(FileInfo fileInfo);

    /**
     * getDuplicates returns all set of files with the same content.
     * @return List of absolute file paths with the same content.
     */
    List<List<String>> getDuplicates();
}
