@echo off
cd ../..
javac -d ../out/production/HW/ javase01/t01/logic/Logic.java
javac -d ../out/production/HW/ javase01/t01/main/Main.java
cd ../out/production/HW/
java javase01/t01/main/Main
pause