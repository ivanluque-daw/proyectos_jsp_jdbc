package org.iesvdm.app_jdbc.dao;

import org.iesvdm.app_jdbc.model.User;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

public interface UserDAO {
    public void create(User user);
    public List<User> getAll();
    public Optional<User> findByUsernameAndPassword(String username, String password);
}