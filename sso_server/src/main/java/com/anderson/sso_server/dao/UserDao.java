package com.anderson.sso_server.dao;

import com.anderson.sso_server.bo.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserDao extends CrudRepository<User, String> {
    public User findByName(String userName);
    public User findByCert(String cert);
    public User findByUuid(String uuid);
}