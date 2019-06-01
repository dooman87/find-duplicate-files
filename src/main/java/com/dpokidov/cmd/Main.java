package com.dpokidov.cmd;

import java.nio.file.Files;
import java.nio.file.Paths;

class Main {
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

        String dir = args[0];
        if (!Files.exists(Paths.get(dir))) {
            printUsage();
            throw new IllegalArgumentException("directory " + dir + " does not exist");
        }

        if (!Files.isDirectory(Paths.get(dir))) {
            printUsage();
            throw new IllegalArgumentException("expected directory, but got regular file");
        }
    }
}