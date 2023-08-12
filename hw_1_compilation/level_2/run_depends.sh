#!/bin/bash

cd depends
javac -sourcepath ./src -d build/classes/ -cp ./lib/commons-math3-3.6.1.jar src/Main.java
java -cp build/classes/:./lib/commons-math3-3.6.1.jar Main
