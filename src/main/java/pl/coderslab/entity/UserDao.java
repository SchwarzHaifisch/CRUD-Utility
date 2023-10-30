package pl.coderslab.entity;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.Arrays;

public class UserDao {
    private static final String QUERRY_ADD_USER = "INSERT INTO Users(username,email,password) VALUES(?,?,?);";
    private static final String QUERRY_UPDATE_RECORD = "UPDATE Users SET username = ?, email = ?, password = ? WHERE id = ?;";
    private static final String QUERRY_SHOW_RECORDS_WITH_ID = "SELECT * FROM Users WHERE id = ?;";
    private static final String QUERRY_REMOVE_RECORD = "DELETE FROM Users where id = ?;";
    private static final String QUERRY_SHOW_ALL_RECORDS = "SELECT * FROM Users;";

    public User create(User user) {
        try (Connection conn = DbUtil.connect()) {
            PreparedStatement statement =
                    conn.prepareStatement(QUERRY_ADD_USER, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, hashPassword(user.getPassword()));
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public User read(int getId) throws SQLException {
        Connection conn = DbUtil.connect();
        PreparedStatement statGetRecordWithId = conn.prepareStatement(QUERRY_SHOW_RECORDS_WITH_ID);
        statGetRecordWithId.setInt(1, getId);
        ResultSet resGetRecordWithId = statGetRecordWithId.executeQuery();
        User user = new User();
        while (resGetRecordWithId.next()) {
            user.setId(resGetRecordWithId.getInt("id"));
            user.setUserName(resGetRecordWithId.getString("username"));
            user.setEmail(resGetRecordWithId.getString("email"));
            user.setPassword(resGetRecordWithId.getString("password"));
        }
        if (user.getUserName() == null || user.getEmail().equalsIgnoreCase("")) {
            return null;
        }
        return user;
    }

    public void update(User user) throws SQLException {
        Connection conn = DbUtil.connect();
        PreparedStatement statUpdateRecord = conn.prepareStatement(QUERRY_UPDATE_RECORD);
        statUpdateRecord.setString(1, user.getUserName());
        statUpdateRecord.setString(2, user.getEmail());
        statUpdateRecord.setString(3, user.getPassword());
        statUpdateRecord.setInt(4, user.getId());
        statUpdateRecord.executeUpdate();
    }

    public void delete(int userId) throws SQLException {
        Connection conn = DbUtil.connect();
        PreparedStatement statDeleteUser = conn.prepareStatement(QUERRY_REMOVE_RECORD);
        statDeleteUser.setInt(1, userId);
        statDeleteUser.executeUpdate();


    }

    public User[] findAll() throws SQLException {
        Connection conn = DbUtil.connect();
        PreparedStatement statFindAll = conn.prepareStatement(QUERRY_SHOW_ALL_RECORDS);
        ResultSet resFindAll = statFindAll.executeQuery();
        User[] users = new User[0];
        while (resFindAll.next()) { //pętla powiększa tabelę o 1
                                     // a nastepnie wpisuje rekord na ostatnie miejsce
            users = Arrays.copyOf(users, users.length + 1);
            users[users.length - 1] = new User(); // tworzę nowy obiekt user bo
                                                    // tablica ma być z obiektów user
            users[users.length - 1].setId(resFindAll.getInt("id"));
            users[users.length - 1].setUserName(resFindAll.getString("username"));
            users[users.length - 1].setEmail(resFindAll.getString("email"));
            users[users.length - 1].setPassword(resFindAll.getString("password"));

        }
        return users;
    }
}

