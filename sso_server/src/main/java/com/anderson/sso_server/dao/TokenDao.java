package com.anderson.sso_server.dao;

import com.anderson.sso_server.bo.Token;
import com.anderson.sso_server.bo.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface TokenDao extends CrudRepository<Token, Long> {
    public Token findByToken(String token);
}