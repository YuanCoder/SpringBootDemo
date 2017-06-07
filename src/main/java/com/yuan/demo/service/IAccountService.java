package com.yuan.demo.service;

import com.yuan.demo.entity.Account;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Yuanjp on 2017/6/6 0006.
 */

public interface IAccountService {

    int add(Account account);

    int update(Account account);

    int delete(int id);

    Account findAccountById(int id);

    List<Account> findAccountList();
}
