package com.pinyougou.service;

import com.pinyougou.pojo.TbSeller;
import com.pinyougou.sellergoods.service.SellerService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 认证类 seller用户登录
 */
public class UserDetailsServiceImpl implements UserDetailsService {
    private SellerService sellerService;

    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<GrantedAuthority> authorities = new ArrayList<>();
        //TODO AuthorityUtils 读取数据库中的权限然后赋值
        authorities.add(new SimpleGrantedAuthority("ROLE_SELLER"));
        TbSeller seller = null;
        User user=null;
        try {
            seller = sellerService.findOne(username);
            user = new User(username, seller.getPassword(), "1".equals(seller.getStatus()), true, true, true, authorities);
        } catch (Exception e) {
            e.printStackTrace();
        }
		return user;
	}
}
