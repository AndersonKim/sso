package com.anderson.slave_2.dao;

import com.anderson.slave_2.bo.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserDao extends CrudRepository<User, String> {
    public User findByName(String userName);
    public User findByCert(String cert);
}