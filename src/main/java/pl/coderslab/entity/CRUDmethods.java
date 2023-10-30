package pl.coderslab.entity;

import java.sql.SQLException;
import java.util.Scanner;

public class CRUDmethods {
    public static void cCreate(){
        User user = new User();
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Enter user name: ");
        user.setUserName(scanner1.nextLine());
        System.out.println("Enter user e-mail: ");
        user.setEmail(scanner1.nextLine());
        System.out.println("Enter user password: ");
        user.setPassword(scanner1.nextLine());
        UserDao.create(user);
        System.out.println("Done");
    }
    public static void uUpdate() throws SQLException {
        User user = new User();
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Enter ID to edit: ");
        int id = scanner1.nextInt();
        user.setId(id);
        scanner1.nextLine();
        System.out.println("Enter new user name: ");
        user.setUserName(scanner1.nextLine());
        System.out.println("Enter new e-mail: ");
        user.setEmail(scanner1.nextLine());
        System.out.println("Enter new password: ");
        user.setPassword(scanner1.nextLine());
        UserDao.update(user);
        System.out.println("Done. New details of record number: "+id);
        System.out.println(UserDao.read(id));
    }
    public static void dDelete() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter ID to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        UserDao.delete(id);
        System.out.println("Removed record with id: "+id);
    }
    public static void rRead() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter ID of record which you want to print: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println(UserDao.read(id));
    }
}
