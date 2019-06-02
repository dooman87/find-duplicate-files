package com.dpokidov;

import java.util.function.Function;

/**
 * Loader loads files from specific location.
 */
public interface Loader {
    /**
     * Loads files from the location specified by uri and executes withFile callback on each entry.
     * <b>Implementation should only call callback on regular items with content (e.g. files)
     * and not on folders. </b>
     * @param uri location from where load files from
     * @param callback callback to call on each entry
     */
    void withFiles(String uri, Function<FileInfo, Void> callback);
}