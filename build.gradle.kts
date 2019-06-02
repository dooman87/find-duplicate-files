/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java Library project to get you started.
 * For more details take a look at the Java Libraries chapter in the Gradle
 * User Manual available at https://docs.gradle.org/5.4.1/userguide/java_library_plugin.html
 */
plugins {
    application
    idea
}

application {
    mainClassName = "com.dpokidov.cmd.Main"
}

repositories {
    // Use jcenter for resolving your dependencies.
    // You can declare any Maven/Ivy/file repository here.
    jcenter()
}

dependencies {
    testImplementation("junit:junit:4.12")
    testImplementation("org.mockito:mockito-core:2.+")
}

