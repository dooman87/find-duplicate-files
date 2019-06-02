package com.dpokidov.cmd;

import com.dpokidov.action.Action;
import com.dpokidov.loader.Loader;
import com.dpokidov.reducer.Reducer;

import java.io.IOException;

class Main {
    private Loader loader;
    private Reducer reducer;
    private Action action;

    public Main(Loader loader, Reducer reducer, Action action) {
        if (loader == null) {
            throw new IllegalArgumentException("loader is required");
        }
        if (reducer == null) {
            throw new IllegalArgumentException("reducer is required");
        }
        if (action == null) {
            throw new IllegalArgumentException("action is required");
        }

        this.loader = loader;
        this.reducer = reducer;
        this.action = action;
    }

    public void run(String uri) throws IOException {
        this.loader.withFiles(uri, file -> {
            this.reducer.add(file);

            return null;
        });

        this.reducer.getResult().forEach(duplicates -> {
            this.action.withDuplicates(duplicates);
        });
    }

    private static void printUsage() {
        System.out.println("Usage: finddups <DIR>");
    }

    public static void main(String[]args){
        if (args.length == 0) {
            printUsage();
            throw new IllegalArgumentException("no input folder defined");
        }

        if (args.length > 1) {
            printUsage();
            throw new IllegalArgumentException("only one parameter allowed");
        }
    }

}