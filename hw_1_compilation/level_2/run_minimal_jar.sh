#!/bin/bash

cd minimal_jar
/c/jdk-17/bin/javac -sourcepath ./src -d build/classes ./src/Main.java
/c/jdk-17/bin/jar cvfm build/jar/main.jar resources/MANIFEST.MF -C build/classes .
/c/jdk-17/bin/java -jar build/jar/main.jar