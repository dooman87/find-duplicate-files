package com.dpokidov.cmd;

import java.nio.file.Files;
import java.nio.file.Paths;

class Main {
    private static void printUsage() {
        System.out.println("Usage: finddups <DIR>");
    }

    public static void main(String[]args){
        System.out.println("It works!");
        if (args.length == 0) {
            printUsage();
            throw new IllegalArgumentException("no input folder defined");
        }

        if (args.length > 1) {
            printUsage();
            throw new IllegalArgumentException("only one parameter allowed");
        }

        String dir = args[0];
        if (!Files.isDirectory(Paths.get(dir))) {
            printUsage();
            throw new IllegalArgumentException("expected directory");
        }
    }
}