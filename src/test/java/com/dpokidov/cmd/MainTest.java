package com.dpokidov.cmd;

import com.dpokidov.Action;
import com.dpokidov.FileInfo;
import com.dpokidov.Loader;
import com.dpokidov.Reducer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MainTest {
    private Main main;
    @Mock
    Loader loader;
    @Mock
    Reducer reducer;
    @Mock
    Action action;

    @Before
    public void setup() throws Exception {
        doAnswer(invocation -> {
            invocation.getArgument(1, Function.class).apply(
                new FileInfo("./src/test/resources/com/dpokidov/cmd/dir/1.txt", "123".getBytes())
            );
            invocation.getArgument(1, Function.class).apply(
                new FileInfo("./src/test/resources/com/dpokidov/cmd/dir/2.txt", "123".getBytes())
            );
            return null;
        }).when(loader).withFiles(eq("./src/test/resources/com/dpokidov/cmd/dir"), any());

        when(reducer.getDuplicates()).thenReturn(
            Arrays.asList(
                Arrays.asList(
                    "./src/test/resources/com/dpokidov/cmd/dir/1.txt",
                    "./src/test/resources/com/dpokidov/cmd/dir/2.txt"
                )
            )
        );

        this.main = new Main(loader, reducer, action);

    }

    @Test(expected = IllegalArgumentException.class)
    public void Main_ErrorWhenNoLoader() {
        new Main(null, mock(Reducer.class), mock(Action.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void Main_ErrorWhenNoReducer() {
        new Main(mock(Loader.class), null, mock(Action.class));
    }

    @Test(expected = IllegalArgumentException.class)
    public void Main_ErrorWhenNoAction() {
        new Main(mock(Loader.class), mock(Reducer.class), null);
    }

    @Test
    public void run_ShouldLoadFiles() throws Exception {
        main.run("./src/test/resources/com/dpokidov/cmd/dir");

        verify(loader).withFiles(eq("./src/test/resources/com/dpokidov/cmd/dir"), any());
    }

    @Test
    public void run_shouldReduceFiles() throws Exception {
        main.run("./src/test/resources/com/dpokidov/cmd/dir");

        verify(reducer).add(new FileInfo("./src/test/resources/com/dpokidov/cmd/dir/1.txt", "123".getBytes()));
        verify(reducer).add(new FileInfo("./src/test/resources/com/dpokidov/cmd/dir/2.txt", "123".getBytes()));
    }

    @Test
    public void run_shouldCallAction() throws Exception {
        main.run("./src/test/resources/com/dpokidov/cmd/dir");

        verify(action).withDuplicates(Arrays.asList("./src/test/resources/com/dpokidov/cmd/dir/1.txt", "./src/test/resources/com/dpokidov/cmd/dir/2.txt"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void main_ThrowErrorIfNoArgument() {
        Main.main(new String[]{});
    }

    @Test(expected = IllegalArgumentException.class)
    public void main_ErrorWhenMoreThanOneArgument() {
        Main.main(new String[]{"one", "two"});
    }

    @Test
    public void main_Ok() {
        String dirPath = Paths.get("./src/test/resources/com/dpokidov/cmd/dir").toAbsolutePath().toString();
        Main.main(new String[]{dirPath});
    }
}