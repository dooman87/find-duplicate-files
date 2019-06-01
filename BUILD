package(default_visibility = ["//visibility:public"])

java_library(
    name = "finddups-lib",
    srcs = glob(["src/main/java/com/dpokidov/cmd/*.java"])
)

java_binary(
    name = "finddups",
    main_class = "com.dpokidov.cmd.Main",
    runtime_deps = [":finddups-lib"],
)

java_test(
    name = "tests",
    srcs = glob(["src/test/java/com/dpokidov/cmd/*.java"]),
    test_class = "com.dpokidov.cmd.MainTest",
    deps = [
        ":finddups-lib",
        "@junit_junit//jar"
    ],
)