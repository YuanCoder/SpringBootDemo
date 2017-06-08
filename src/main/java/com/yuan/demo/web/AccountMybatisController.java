package com.yuan.demo.web;

import com.yuan.demo.entity.Account;
import com.yuan.demo.service.IAccountService;
import com.yuan.demo.service.IAccountServiceMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Mybatis 控制层
 * Created by Yuanjp on 2017/6/7 0007.
 */
@RestController
@RequestMapping("/mybatisAccount")
public class AccountMybatisController {

    @Autowired
    IAccountServiceMybatis iAccountService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Account> getAccounts() {
        return iAccountService.findAccountList();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Account getAccountById(@PathVariable("id") int id) {
        return iAccountService.findAccount(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String updateAccount(@PathVariable("id") int id, @RequestParam(value = "name", required = true) String name,
                                @RequestParam(value = "money", required = true) double money) {
        int t= iAccountService.update(name,money,id);
        if(t==1) {
            return "success";
        }else {
            return "fail";
        }

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(value = "id")int id) {
        int t= iAccountService.delete(id);
        if(t==1) {
            return "success";
        }else {
            return "fail";
        }

    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String postAccount(@RequestParam(value = "name") String name,
                              @RequestParam(value = "money") double money) {

        int t= iAccountService.add(name,money);
        if(t==1) {
            return "success";
        }else {
            return "fail";
        }

    }
}
