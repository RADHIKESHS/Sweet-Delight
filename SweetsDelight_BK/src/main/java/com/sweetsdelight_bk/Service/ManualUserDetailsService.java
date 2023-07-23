package com.sweetsdelight_bk.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sweetsdelight_bk.Model.User;
import com.sweetsdelight_bk.Repository.UserRepo;


@Service
public class ManualUserDetailsService implements UserDetailsService {
	
	
	@Autowired
	private UserRepo uRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = uRepo.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        User us = user.get();

        List<GrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority autho = new SimpleGrantedAuthority("ROLE_" + us.getRole().toUpperCase());
        authorities.add(autho);

//        // Print all authorities
//        System.out.println("Authorities for user " + us.getUsername() + ":");
//        for (GrantedAuthority authority : authorities) {
//            System.out.println(authority.getAuthority());
//        }

        org.springframework.security.core.userdetails.User secUser = new org.springframework.security.core.userdetails.User(
                us.getUsername(),
                us.getPassword(),
                authorities
        );

        return secUser;
    }

}
