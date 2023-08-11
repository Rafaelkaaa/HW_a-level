#!/bin/bash
/c/jdk-17/bin/javac -sourcepath ./src -d build/classes/ -cp ./lib/commons-math3-3.6.1.jar src/Main.java
/c/jdk-17/bin/java -cp build/classes/:./lib/commons-math3-3.6.1.jar Main
