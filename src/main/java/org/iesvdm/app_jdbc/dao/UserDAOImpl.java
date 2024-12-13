package org.iesvdm.app_jdbc.dao;

import org.iesvdm.app_jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
                User socio = new User();

                socio.setId(rs.getInt("id"));
                socio.setUsername(rs.getString("username"));
                socio.setPassword(rs.getString("password"));

                usersList.add(socio);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, s, rs);
        }

        return usersList;
    }
}
