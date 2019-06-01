# find-duplicate-files

Command line tool to find duplicate files in given folder. The application finds duplicated by its content 
and will output relative paths of duplicates.

## Usage

Usage:
`find-duplicate-files [DIR]`

## Building

Build the application

`$ bazel build :funddups`

Test the application:

`$ bazel test :tests`