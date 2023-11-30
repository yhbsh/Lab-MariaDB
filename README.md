# Lab NAL - 230

- Make sure you have a mariadb instance running on your machine on port 3306
- Replace url, name, password with correct values in Main.java, the values below are for my specific environment

```java 
final String url = "jdbc:mariadb://localhost:3306/test";
final String user = "home";
final String password = "password";
```

# Running
```zsh
make
```
or
```zsh
javac -cp .:mariadb.jar Main.java
java -cp .:mariadb.jar Main
```
