package com.hanselone.demo.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Getter @Setter
public class Post extends BaseCreated{

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2",strategy = "uuid2")
    private String post_id;

    @Column(length = 20,nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String bios;
    
    private int suggestion; //추천수

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User writer;
}
