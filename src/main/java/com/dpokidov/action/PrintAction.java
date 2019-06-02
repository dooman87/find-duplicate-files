package com.dpokidov.action;

import java.util.List;

/**
 * Simple action that prints input files to stdout.
 */
public class PrintAction implements Action {
    private String prefix;

    public PrintAction() {
        this(null);
    }

    public PrintAction(String prefix) {
        this.prefix = prefix;
    }

    void print(String message) {
        System.out.println(message);
    }

    @Override
    public void withDuplicates(List<String> uris) {
        StringBuilder stringBuilder = new StringBuilder("Found duplicates:\n");
        uris.forEach(f -> {
            String trimmedF = f;
            if (prefix != null && f.startsWith(prefix)) {
                trimmedF = f.substring(prefix.length());
            }

            stringBuilder.append("\t").append(trimmedF).append("\n");
        });

        print(stringBuilder.toString());
    }
}
