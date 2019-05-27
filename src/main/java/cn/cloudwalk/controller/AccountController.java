package cn.cloudwalk.controller;

import cn.cloudwalk.Service.MultiThreadService;
import cn.cloudwalk.model.Account;
import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private MultiThreadService multiThreadService;

    @CreateCache(name = "account", cacheType = CacheType.BOTH)
    public Cache<String, Account> cache;

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
//    @Cached(name = "account:", key = "#id",expire = 60 * 30, cacheType = CacheType.BOTH)
    public Account getAccount(@PathVariable String id) {
        Account account = cache.get(id);
        if (account != null) {
            return account;
        }
        account = new Account();
        account.setId(id);
        account.setUsername("rose");
        account.setPhone("110");
        cache.put(id, account);
        return account;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @CacheUpdate(name = "account:", value = "#account", key = "#account.id")
    public Account updateAccount(@RequestBody Account account) {
        return account;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @CacheInvalidate(name = "account:", key = "#id")
    public Account deleteAccount(@PathVariable String id) {
        return null;
    }

    @RequestMapping(value = "/play/order", method = RequestMethod.GET)
    public String playOrder() {
        for (int i = 0; i < 20; i++) {
            new Thread(){
                @Override
                public void run() {
                    multiThreadService.playOrder();
                }
            }.start();
        }
        return "success";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

}
