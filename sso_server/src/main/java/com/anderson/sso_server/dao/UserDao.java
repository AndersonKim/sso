package com.anderson.sso_server.dao;

import com.anderson.sso_server.bo.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserDao extends CrudRepository<User, Long> {
    public User findByUserName(String userName);
}