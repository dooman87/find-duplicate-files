package com.dpokidov.model;

import java.util.Arrays;
import java.util.Objects;

public class FileInfo {
    private String path;
    private byte[] content;

    public FileInfo(String path, byte[] content) {
        this.path = path;
        this.content = content;
    }

    public String getPath() {
        return path;
    }

    public byte[] getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileInfo fileInfo = (FileInfo) o;
        return Objects.equals(getPath(), fileInfo.getPath()) &&
                Arrays.equals(getContent(), fileInfo.getContent());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getPath());
        result = 31 * result + Arrays.hashCode(getContent());
        return result;
    }
}
