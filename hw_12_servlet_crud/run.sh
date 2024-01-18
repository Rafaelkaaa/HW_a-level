mvn clean
mvn package
kill -9 $(lsof -t -i:8080)
rm -rf ./apache-tomcat-10.1.16/webapps/*
cp ./target/hw_12_servlet_crud.war ./apache-tomcat-10.1.16/webapps/
sh ./apache-tomcat-10.1.16/bin/startup.sh