package com.hanselone.demo.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseCreated{

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2",strategy = "uuid2")
    private String post_id;

    @Column(length = 20)
    private String title;

    @Column(nullable = true)
    private String bios;

    @Column(columnDefinition = "integer default 0")
    private int suggestion; //추천수

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User writer;

    @OneToMany
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Builder
    public Post(String id,String title,String bios,User writer){
        this.post_id = id;
        this.title = title;
        this.bios = bios;
        this.writer = writer;
    }

    public void changeSuggestion(int number){
        this.suggestion += number;
    }

}
