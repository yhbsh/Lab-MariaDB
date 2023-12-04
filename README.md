# Lab NAL - 230

## Demo

https://github.com/HoussemBousmaha/Lab-MariaDB/assets/86262467/f5e3d109-b3cb-4ed5-9681-f9bc3c242251

## Source Code


https://github.com/HoussemBousmaha/Lab-MariaDB/assets/86262467/885cb0db-fd52-48ee-914e-b3569032c6c9




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
