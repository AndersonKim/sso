package com.anderson.slave_1.dao;

import com.anderson.slave_1.bo.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserDao extends CrudRepository<User, String> {
    public User findByName(String userName);
    public User findByCert(String cert);
}