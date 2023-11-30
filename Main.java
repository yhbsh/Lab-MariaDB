import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        final String url = "jdbc:mariadb://localhost:3306/test";
        final String user = "home";
        final String password = "password";

        final Connection conn = DriverManager.getConnection(url, user, password);

        final Statement statement = conn.createStatement();

        final String createDatabase = "CREATE DATABASE IF NOT EXISTS test";
        statement.execute(createDatabase);

        final String createTable = "CREATE TABLE IF NOT EXISTS student ("
                                   + "StudentID INT PRIMARY KEY NOT NULL, "
                                   + "Name VARCHAR(255) NOT NULL, "
                                   + "Department VARCHAR(255) NOT NULL, "
                                   + "GPA VARCHAR(255) NOT NULL)";

        statement.execute(createTable);

        while (true) {
            System.out.print("\033[H\033[2J");

            System.out.println("Student tables: ");
            System.out.println();
            System.out.println("1) Insert new student");
            System.out.println("2) Display all the students");
            System.out.println("3) Alter GPA");
            System.out.println("4) Exit");
            System.out.println();
            System.out.print("Choose an operation: ");

            final Scanner scanner = new Scanner(System.in);
            final String choice = scanner.nextLine();

            System.out.print("\033[H\033[2J");

            switch (choice) {
            case "1": {
                do {
                    System.out.println("Student - INSERTION");
                    System.out.println("-------------------");
                    System.out.println();

                    System.out.print("StudentID: ");
                    final int id = scanner.nextInt();

                    System.out.print("Student name: ");
                    final String name = scanner.next();

                    System.out.print("Department: ");
                    final String department = scanner.next();

                    System.out.print("GPA: ");
                    final String gpa = scanner.next();

                    statement.execute("INSERT INTO student VALUES (" + id + ", '" + name + "', '" + department + "', '" + gpa + "')");

                    System.out.print("Insert another record (Y/N)?: ");
                } while (scanner.next().equalsIgnoreCase("Y"));
                break;
            }
            case "2": {
                System.out.println("Student - DISPLAY");
                System.out.println("-------------------");
                System.out.println();

                final ResultSet resultSet = statement.executeQuery("SELECT * FROM student");
                while (resultSet.next()) {
                    final int id = resultSet.getInt("StudentID");
                    final String name = resultSet.getString("Name");
                    final String department = resultSet.getString("Department");
                    final String gpa = resultSet.getString("GPA");
                    System.out.printf("%-10d%-10s\t%-10s\t%-10s\n", id, name, department, gpa);
                }

                if (!resultSet.previous())
                    System.out.println("No records found!");

                System.out.println();
                scanner.nextLine();
                break;
            }
            case "3": {
                System.out.println("Student - ALTER GPA");
                System.out.println("-------------------");

                System.out.print("StudentID: ");
                final int id = scanner.nextInt();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM student WHERE StudentID = " + id);
                if (!resultSet.first()) {
                    scanner.nextLine();

                    System.out.println();
                    System.out.println("Student not found!");
                    System.out.println();

                    scanner.nextLine();
                    break;
                }

                final double gpa = Double.parseDouble(resultSet.first() ? resultSet.getString("GPA") : "0");
                System.out.println("Current GPA: " + gpa);

                System.out.print("Enter value [0-5]: ");
                final int value = scanner.nextInt();
                if (value < 0 || value > 5) {
                    System.out.print("\033[H\033[2J");
                    System.out.println("Invalid value!");
                    scanner.nextLine();
                }

                double increase = 0.0d;
                if (value > gpa) {
                    increase = 0.1d;
                } else if (value < gpa) {
                    increase = -0.1d;
                } else {
                    System.out.println("GPA unchanged!");
                }

                statement.execute("UPDATE student SET GPA = " + (gpa + increase) + " WHERE StudentID = " + id);

                System.out.println();
                scanner.nextLine();
                break;
            }
            case "4": {
                System.exit(0);
            }
            default:
            }
        }
    }
}

