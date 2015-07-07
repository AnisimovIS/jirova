package com.springapp.services.security;

import com.springapp.entity.UserPrincipal;
import com.springapp.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserPrincipal userPrincipal = (UserPrincipal)userService.get(login);
        if (userPrincipal==null){
            throw new UsernameNotFoundException("UserNotFound");
        }
/*
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority("ROLE_" + UserRoleEnum.USER));
        roles.add(new SimpleGrantedAuthority("ROLE_" + UserRoleEnum.ANONYMOUS));
*/
        UserDetails userDetails = new User(userPrincipal.getLogin(),userPrincipal.getPassword(),userPrincipal.getRoles());
        return userDetails;
    }

}
