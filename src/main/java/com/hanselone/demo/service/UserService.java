package com.hanselone.demo.service;

import com.hanselone.demo.domain.User;
import com.hanselone.demo.dto.UserDto;
import com.hanselone.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Transactional
    public String singUp(UserDto userDto){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return userRepository.save(userDto.toEntity());
    }


    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        User userWrapper = userRepository.findByAccount(account).get(0);
        List<GrantedAuthority> authorityList = new ArrayList<>();

        if(account.equals("admin")){
            authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        else{
            authorityList.add(new SimpleGrantedAuthority("NONE"));
        }
        return new org.springframework.security.core.userdetails.User(userWrapper.getAccount(),
                userWrapper.getPassword(),authorityList);
    }
}
