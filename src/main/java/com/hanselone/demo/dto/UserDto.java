package com.hanselone.demo.dto;

import com.hanselone.demo.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class UserDto {

    private String id;
    private String name;
    private String account;
    private String password;

    public User toEntity(){
        return User.builder()
                .id(id)
                .name(name)
                .account(account)
                .password(password)
                .build();
    }

    @Builder
    public UserDto(String id,String name,String account,String password){
        this.id = id;
        this.name= name;
        this.account = account;
        this.password = password;
    }
}
