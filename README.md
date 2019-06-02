# find-duplicate-files

Command line tool to find duplicate files in given folder. The application finds duplicated by its content 
and will output relative paths of duplicates.

## Usage

You need to install Java 8+ on your computer.

Usage:
`find-duplicate-files [DIR]`

## Releasing

Build the application

`$ gradle distZip`

A ready to go artifact will be generated in `build/distributions/find-duplicate-files.zip`

## Deving

The application is using [gradle](gradle.org) as build system.

Run unit tests:

`$ gradle test`