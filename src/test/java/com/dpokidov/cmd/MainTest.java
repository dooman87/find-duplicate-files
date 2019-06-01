package com.dpokidov.cmd;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class MainTest {
    @Test(expected = IllegalArgumentException.class)
    public void main_ThrowErrorIfNoArgument() {
        Main.main(new String[]{});
    }

    @Test(expected = IllegalArgumentException.class)
    public void main_ErrorWhenMoreThanOneArgument() {
        Main.main(new String[]{"one", "two"});
    }

    @Test(expected = IllegalArgumentException.class)
    public void main_ErrorWhenPassedNotAFolder() {
        String notAFolderPath = Paths.get("./src/test/resources/com/dpokidov/cmd/not-a-dir").toAbsolutePath().toString();
        Main.main(new String[]{notAFolderPath});
    }

    @Test
    public void main_Ok() {
        System.err.printf("Current path %s\n", Paths.get(".").toAbsolutePath().toString());
        String notAFolderPath = Paths.get("./src/test/resources/com/dpokidov/cmd/dir").toAbsolutePath().toString();
        Main.main(new String[]{notAFolderPath});
    }
}