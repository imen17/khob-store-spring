package com.project.khob.repositories;

import com.project.khob.domain.entities.CartItem;
import com.project.khob.domain.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository   extends CrudRepository<Category, Integer> {
}
