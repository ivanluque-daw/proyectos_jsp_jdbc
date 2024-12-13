package org.iesvdm.app_jdbc.dao;

import org.iesvdm.app_jdbc.model.User;
import org.iesvdm.app_jdbc.util.PasswordHasher;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl extends AbstractDAOImpl implements UserDAO {
    @Override
    public void create(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSet rsGenKeys = null;

        try {
            conn = connectDB();
            ps = conn.prepareStatement("INSERT INTO users (nombre, password) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());

            rsGenKeys = ps.getGeneratedKeys();
            if (rsGenKeys.next()) {
                user.setId(rsGenKeys.getInt(1));
            }
        } catch (SQLException | ClassNotFoundException  e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }

    @Override
    public List<User> getAll() {
        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;

        List<User> usersList = new ArrayList<>();

        try {
            conn = connectDB();
            s = conn.createStatement();

            rs = s.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                User user = new User();

                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));

                usersList.add(user);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, s, rs);
        }

        return usersList;
    }

    @Override
    public Optional<User> findByUsernameAndPassword(String username, String password) {
        Connection conn = null;
        PreparedStatement s = null;
        ResultSet rs = null;

        try {
            conn = connectDB();
            s = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            s.setString(1, username);
            s.setString(2, PasswordHasher.hashPassword(password));

            rs = s.executeQuery();

            if (rs.next()) {
                User user = new User();

                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));

                return Optional.of(user);
            }
        } catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, s, rs);
        }

        return Optional.empty();
    }
}
