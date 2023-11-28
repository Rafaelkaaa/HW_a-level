package org.example.dao;

import org.example.entity.Account;
import org.example.entity.User;

import java.util.Collection;
import java.util.UUID;

public class AccountCrudDaoImpl extends BaseCrudDaoImpl<Account> {

    public Collection<Account> findByUserId(UUID userId) {
        return entityManager.find(User.class, userId).getAccounts();
    }
}
