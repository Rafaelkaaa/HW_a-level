#!/bin/bash

cd minimal_proj
/c/jdk-17/bin/javac -sourcepath ./src -d build/classes ./src/Main.java
/c/jdk-17/bin/java -cp build/classes Main
