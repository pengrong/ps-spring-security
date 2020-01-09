package com.inpsur.gcloud.ps.sso.jis;

import com.inpsur.gcloud.ps.core.userdetails.PsUserDetails;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;

@CommonsLog
@Component
public class JISUserDetailsService  {


    public PsUserDetails loadUserByUuid(String uuid) throws UsernameNotFoundException {
        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
        // 用户名密码，用户授权、过期、锁定等信息
        PsUserDetails user = new PsUserDetails(uuid, "", authorities);

        // 1.根据 uuid 查询
        // 2.查询到用户，获取并更新用户详细信息
        // 3.查询不到，插入用户和用户详细信息
        return user;
    }
}
