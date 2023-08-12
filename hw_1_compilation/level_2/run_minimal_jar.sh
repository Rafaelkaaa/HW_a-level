#!/bin/bash

cd minimal_jar
javac -sourcepath ./src -d build/classes ./src/Main.java
jar cvfm build/jar/main.jar resources/MANIFEST.MF -C build/classes .
java -jar build/jar/main.jar