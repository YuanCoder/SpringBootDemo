package com.yuan.demo.dao;

import com.yuan.demo.entity.Account;

import java.util.List;

/**
 * Created by Yuanjp on 2017/6/5 0005.
 */
public interface IAccountDAO {
    int add(Account account);

    int update(Account account);

    int delete(int id);

    Account findAccountById(int id);

    List<Account> findAccountList();
}
