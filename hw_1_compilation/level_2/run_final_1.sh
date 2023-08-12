#!/bin/bash

cd final_1
javac -sourcepath ./src -d build/classes/ -cp ./lib/commons-math3-3.6.1.jar src/Main.java
cp -r lib/*.jar build/jar
jar cvfm build/jar/main.jar resources/MANIFEST.MF -C build/classes .
java -jar build/jar/main.jar