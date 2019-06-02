package com.dpokidov.reducer;

import com.dpokidov.model.FileInfo;

import java.util.Collection;
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
     * getResult returns all set of files with the same content.
     * @return List of absolute file paths with the same content.
     */
    Collection<List<String>> getResult();
}
