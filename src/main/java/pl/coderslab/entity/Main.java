package pl.coderslab.entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection conn = DbUtil.connect();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("What do you want to do: ");
            System.out.println("P - print all records \nC - create record \nU - update record \nR - remove record \nD - delete record \nQ - quit program");
            String input = scanner.nextLine();
            char[] charses = input.toCharArray();
            if (Character.isLowerCase(charses[0])){
                charses[0] = Character.toUpperCase(charses[0]);
            }
            switch (charses[0]) {
                case 'P':
                    DbUtil.printData(conn, DbUtil.QUERRY_SHOW_ALL_RECORDS, "id", "username", "email", "password");
                    break;
                case 'C':
                    CRUDmethods.cCreate();
                    break;
                case 'U':
                    CRUDmethods.uUpdate();
                    break;
                case 'R':
                    CRUDmethods.rRead();
                    break;
                case 'D':
                    CRUDmethods.dDelete();
                    break;
                case 'Q':
                    System.exit(0);
                    break;
            }
        }
    }
}