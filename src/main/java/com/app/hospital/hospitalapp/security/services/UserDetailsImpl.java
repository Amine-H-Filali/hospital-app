package com.app.hospital.hospitalapp.security.services;


import com.app.hospital.hospitalapp.security.entities.AppRole;
import com.app.hospital.hospitalapp.security.entities.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class UserDetailsImpl implements UserDetailsService {

    private AccountService accountService;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
               AppUser appUser= accountService.loadUserByUserName(username);
               if (appUser==null) throw  new UsernameNotFoundException(String.format("User %s not found",username));

        String[] roles = appUser.getRoles().stream().map((AppRole::getRole)).toArray(String[]::new);


        UserDetails userDetails= User.withUsername(appUser.getUsername())
                       .password(appUser.getPassword())
                       .roles(roles)
                       .build();
        return userDetails;
    }
}
