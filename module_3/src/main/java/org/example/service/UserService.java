package org.example.service;

import org.example.dao.BaseCrudDao;
import org.example.dao.UserCrudDaoImpl;
import org.example.entity.User;

import java.util.UUID;

public class UserService implements BaseCrudService<User> {
    private BaseCrudDao<User> userDao = new UserCrudDaoImpl();

    @Override
    public void create(User entity) {
        userDao.create(entity);
    }

    @Override
    public User read(UUID id) {
        return userDao.read(id, User.class);
    }

    @Override
    public void update(User entity) {
        userDao.update(entity);
    }

    @Override
    public void delete(UUID id) {
        userDao.delete(id, User.class);
    }
}
