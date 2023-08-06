package com.example.security04.security;

import com.example.security04.InitCommandLineRunner;
import com.example.security04.entity.JPAUsuario;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProvider implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<JPAUsuario> list = InitCommandLineRunner.listUsuarios;

        JPAUsuario appUser = list.stream().filter(x -> username.equals(x.getUsername())).findFirst().orElse(null);
        if (appUser == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found");
        }

        return org.springframework.security.core.userdetails.User//
                .withUsername(username)//
                .password(appUser.getPassword())//
                .authorities(appUser.getRoles())//
                //.authorities("ADMIN")//
                .accountExpired(false)//
                .accountLocked(false)//
                .credentialsExpired(false)//
                .disabled(false)//
                .build();
    }
}
