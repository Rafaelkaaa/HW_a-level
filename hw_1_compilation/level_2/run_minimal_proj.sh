#!/bin/bash

cd minimal_proj
javac -sourcepath ./src -d build/classes ./src/Main.java
java -cp build/classes Main
