package com.dpokidov.action;

import org.junit.Test;

import java.util.Arrays;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class PrintActionTest {
    @Test
    public void withDuplicates_ShouldPrint() {
        PrintAction action = spy(PrintAction.class);

        action.withDuplicates(Arrays.asList("one.txt", "two.txt"));

        verify(action).print("Found duplicates:\n\tone.txt\n\ttwo.txt\n");
    }

    @Test
    public void withDuplicates_ShouldRemovePrefix() {
        PrintAction action = spy(new PrintAction("withPrefix/"));

        action.withDuplicates(Arrays.asList("withPrefix/one.txt", "noprefix/two.txt"));

        verify(action).print("Found duplicates:\n\tone.txt\n\tnoprefix/two.txt\n");
    }
}
