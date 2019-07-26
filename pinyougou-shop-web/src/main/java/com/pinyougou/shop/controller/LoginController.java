package com.pinyougou.shop.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取登录用户名的类
 * @author Administrator
 *
 */
@RequestMapping("/login")
@RestController
public class LoginController {

    @RequestMapping("/name")
    private Map name() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<Object,String> map = new HashMap<>();
        map.put("loginName", name);
        return map;
    }
}
