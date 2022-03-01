package com.hanselone.demo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class User {

    @Id @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2",strategy = "uuid2")
    private String user_id;

    @Column(length = 10,nullable = false)
    private String name;

    @Column(length = 20,nullable = false,unique = true)
    private String account;

    @Column(length = 255,nullable = false)
    private String password;

    @CreatedDate
    private LocalDateTime created;

    @Column(columnDefinition = "TEXT default '내용 없음'")
    private String bios;

    @OneToMany(mappedBy = "writer",cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    protected User() {
    }

    @Builder
    public User(String id,String name,String account,String password){
        this.user_id = id;
        this.name = name;
        this.account = account;
        this.password = password;
    }

}
