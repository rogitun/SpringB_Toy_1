package com.hanselone.demo.repository;

import com.hanselone.demo.domain.Category;
import com.hanselone.demo.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {
    private final EntityManager em;

    private final CategoryDto categoryDto = new CategoryDto();


}
