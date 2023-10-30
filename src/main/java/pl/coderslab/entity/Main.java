package pl.coderslab.entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection conn = DbUtil.connect();
        Scanner scanner = new Scanner(System.in);
        System.out.println("What do you want to do: ");
        System.out.println("P - print all records \nC - create record \nU - update record \nR - remove record \nD - delete record");
        String input = scanner.nextLine();
        switch (input){
            case "P":

                DbUtil.printData(conn, DbUtil.QUERRY_SHOW_ALL_RECORDS, "id", "username","email","password");
                break;
        }
//        UserDao userDao = new UserDao();
//        System.out.println(userDao.read(1));
//        System.out.println("------------------------------------------------");
//        User user = new User();
//        user.setUserName("11111");
//        user.setEmail("j22222");
//        user.setPassword("33333");
//        user.setId(1);
//        userDao.update(user);
//        System.out.println(userDao.read(1));
//        System.out.println("-------------------------------------");
//        userDao.delete(3);
//        System.out.println(Arrays.toString(userDao.findAll()));
//        System.out.println("-----------------------------------------------");
//        User user1 = new User();
//        user1.setId(2);
//        user1.setUserName("Imie");
//        user1.setEmail("email");
//        user1.setPassword("Hasło");
//        userDao.create(user1);
//        System.out.println(userDao.read(2));
//        System.out.println(Arrays.toString(userDao.findAll()));
    }

}