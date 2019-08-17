package org.hlamito.controllers;

import org.hlamito.entities.Role;
import org.hlamito.entities.User;
import org.hlamito.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        System.out.println(user.getEmail() + " OUR LOGIN!!");
        if(user == null){
            System.out.println("is NULL!");
            throw new UsernameNotFoundException("User not authorized.");
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        System.out.println("rabotaet");

        for (Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
    }
}
