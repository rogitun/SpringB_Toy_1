package com.hanselone.demo.domain;

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

    @CreatedDate
    private LocalDateTime created;

    @Column(columnDefinition = "TEXT default '내용 없음'")
    private String bios;

    @OneToMany(mappedBy = "writer",cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();
}
