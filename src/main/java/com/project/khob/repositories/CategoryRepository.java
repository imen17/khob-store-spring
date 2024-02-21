package com.project.khob.repositories;

import com.project.khob.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long>  {
}
