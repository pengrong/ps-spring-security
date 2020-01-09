package com.inpsur.gcloud.ps.uc.userdetails;

import com.inpsur.gcloud.ps.core.userdetails.PsUserDetails;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@CommonsLog
@Component
public class PsUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String passwd = passwordEncoder.encode("123");
        log.info("加密后的密码" + passwd);
        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
        // 用户名密码，用户授权、过期、锁定等信息
        PsUserDetails user = new PsUserDetails(username, passwd, authorities);

        return user;
    }



}
