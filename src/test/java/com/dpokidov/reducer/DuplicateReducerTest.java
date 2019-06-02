package com.dpokidov.reducer;

import com.dpokidov.model.FileInfo;
import com.dpokidov.loader.Loader;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DuplicateReducerTest {

    @Test(expected = IllegalArgumentException.class)
    public void DuplicateReducer_ErrorWhenNoLoader() throws Exception {
        new DuplicateReducer(null);
    }

    @Test
    public void add_ShouldHandleNull() {
        Loader loaderMock = mock(Loader.class);
        Reducer reducer = new DuplicateReducer(loaderMock);

        reducer.add(null);
    }

    @Test
    public void getDuplicates_ShouldHaveOnlyDuplicates() throws Exception {
        Loader loaderMock = mock(Loader.class);
        Reducer reducer = new DuplicateReducer(loaderMock);

        when(loaderMock.loadFile("1.txt")).thenReturn(new FileInfo("1.txt", "123".getBytes()));
        when(loaderMock.loadFile("2.txt")).thenReturn(new FileInfo("2.txt", "123".getBytes()));
        when(loaderMock.loadFile("empty.txt")).thenReturn(new FileInfo("empty.txt", "".getBytes()));

        reducer.add(new FileInfo("1.txt", "123".getBytes()));
        reducer.add(new FileInfo("2.txt", "123".getBytes()));
        reducer.add(new FileInfo("empty.txt", "".getBytes()));

        assertEquals(Arrays.asList(Arrays.asList("1.txt", "2.txt")), reducer.getResult());
    }

    @Test
    public void getDuplicates_ShouldCheckContent() throws Exception {
        Loader loaderMock = mock(Loader.class);
        Reducer reducer = new DuplicateReducer(loaderMock);

        when(loaderMock.loadFile("1.txt")).thenReturn(new FileInfo("1.txt", "123".getBytes()));
        when(loaderMock.loadFile("2.txt")).thenReturn(new FileInfo("2.txt", "321".getBytes()));

        reducer.add(new FileInfo("1.txt", "123".getBytes()));
        reducer.add(new FileInfo("2.txt", "321".getBytes()));

        assertTrue(reducer.getResult().isEmpty());
    }

    @Test
    public void getDuplicates_ShouldHandleErrorWhenLoadingFile() throws Exception {
        Loader loaderMock = mock(Loader.class);
        Reducer reducer = new DuplicateReducer(loaderMock);

        when(loaderMock.loadFile("1.txt")).thenReturn(new FileInfo("1.txt", "123".getBytes()));
        when(loaderMock.loadFile("2.txt")).thenThrow(new IOException("Sorry..."));

        reducer.add(new FileInfo("1.txt", "123".getBytes()));
        reducer.add(new FileInfo("2.txt", "123".getBytes()));

        assertTrue(reducer.getResult().isEmpty());
    }
}
