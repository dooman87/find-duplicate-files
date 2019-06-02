package com.dpokidov;

import java.util.List;

/**
 * Action that will be called for duplicated
 */
public interface Action {
    /**
     * withDuplicates should perform an action with
     * files that have the same content, e.g. print
     * to stdout, send by email, etc
     * @param uris list of file names with the same content
     */
    void withDuplicates(List<String> uris);
}
