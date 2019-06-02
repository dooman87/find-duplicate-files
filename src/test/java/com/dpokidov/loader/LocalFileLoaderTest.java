package com.dpokidov.loader;

import com.dpokidov.model.FileInfo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.io.File;
import java.util.function.Function;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class LocalFileLoaderTest {
    Loader loader;

    @Before
    public void setup() {
        loader = new LocalFileLoader();
    }

    @Test(expected = IllegalArgumentException.class)
    public void withFile_ErrorWhenCallbackIsNotPassed() throws Exception {
        loader.withFiles("./src/test/resources/com/dpokidov/dir", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void withFile_ErrorWhenUriIsNotPassed() throws Exception {
        loader.withFiles(null, mock(Function.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void withFile_ErrorWhenUriIsWhitespaces() throws Exception {
        loader.withFiles("    ", mock(Function.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void withFiles_ErrorWhenPassedNotAFolder() throws Exception {
        loader.withFiles("./src/test/resources/com/dpokidov/not-a-dir", mock(Function.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void withFiles_ErrorWhenDirDoesnotExist() throws Exception {
        loader.withFiles("./src/test/resources/com/dpokidov/where-is-it", mock(Function.class));
    }

    @Test
    public void withFiles_ShouldCallCallback() throws Exception {
        Function callbackMock = mock(Function.class);
        loader.withFiles("./src/test/resources/com/dpokidov/dir", callbackMock);

        ArgumentCaptor<FileInfo> argument = ArgumentCaptor.forClass(FileInfo.class);
        verify(callbackMock, times(3)).apply(argument.capture());

        assertTrue(argument.getAllValues().contains(new FileInfo("1.txt", "123".getBytes())));
        assertTrue(argument.getAllValues().contains(new FileInfo("subdir" + File.separator + "1-dup.txt", "123".getBytes())));
        assertTrue(argument.getAllValues().contains(new FileInfo("subdir" + File.separator + "empty.txt", "".getBytes())));
    }

    @Test
    public void withFiles_ShouldTrimUri() throws Exception {
        Function callbackMock = mock(Function.class);
        loader.withFiles("  ./src/test/resources/com/dpokidov/dir   ", callbackMock);

        verify(callbackMock, times(3)).apply(any());
    }
}