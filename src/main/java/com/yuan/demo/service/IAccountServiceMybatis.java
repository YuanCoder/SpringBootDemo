package com.yuan.demo.service;

import com.yuan.demo.dao.AccountMapper;
import com.yuan.demo.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * mybatis
 * Created by Yuanjp on 2017/6/7 0007.
 */
@Service
public interface IAccountServiceMybatis {



    public int add(String name, double money);

    public int update(String name, double money, int id);

    public int delete(int id);

    public Account findAccount(int id);

    public List<Account> findAccountList();
}
