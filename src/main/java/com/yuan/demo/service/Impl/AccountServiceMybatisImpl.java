package com.yuan.demo.service.Impl;

import com.yuan.demo.dao.AccountMapper;
import com.yuan.demo.entity.Account;
import com.yuan.demo.service.IAccountServiceMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * mybatis 
 * Created by Yuanjp on 2017/6/7 0007.
 */
@Service
public class AccountServiceMybatisImpl implements IAccountServiceMybatis {

    @Autowired
    private AccountMapper accountMapper;

    public int add(String name, double money) {
        return accountMapper.add(name, money);
    }
    public int update(String name, double money, int id) {
        return accountMapper.update(name, money, id);
    }
    public int delete(int id) {
        return accountMapper.delete(id);
    }
    public Account findAccount(int id) {
        return accountMapper.findAccount(id);
    }
    public List<Account> findAccountList() {
        return accountMapper.findAccountList();
    }
}
