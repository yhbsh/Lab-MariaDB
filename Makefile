main: Main.java
	javac -cp .:mariadb.jar Main.java && java -cp .:mariadb.jar Main && rm *.class
