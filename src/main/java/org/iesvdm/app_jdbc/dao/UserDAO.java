package org.iesvdm.app_jdbc.dao;

import org.iesvdm.app_jdbc.model.User;

import java.util.List;

public interface UserDAO {
    public void create(User user);
    public List<User> getAll();
}