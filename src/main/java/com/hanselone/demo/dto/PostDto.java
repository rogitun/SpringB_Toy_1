package com.hanselone.demo.dto;

import com.hanselone.demo.domain.Post;
import com.hanselone.demo.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    private String id;
    private String title;
    private String bios;
    private String username;
    private User writer;
    private int suggestion;

    public Post toEntity(){
        return Post.builder()
                .id(id)
                .title(title)
                .bios(bios)
                .writer(writer)
                .build();
    }

    @Builder
    public PostDto(String id,String title,String bios,String username){
        this.id = id;
        this.title =title;
        this.bios = bios;
        this.username = username;
    }

}
