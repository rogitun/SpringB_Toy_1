package com.hanselone.demo.dto;

import com.hanselone.demo.domain.Category;
import lombok.*;


@Getter
@Setter
public class CategoryDto {
    private final Category it = new Category();
    private final Category free = new Category();
    private final Category device = new Category();

    public CategoryDto(){
        it.setName("IT");
        device.setName("가전");
        free.setName("자유");
        free.categorySetting(it);
        free.categorySetting(device);
    }

}
