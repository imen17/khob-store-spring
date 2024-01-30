package com.project.khob.repositories;

import com.project.khob.domain.entities.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<Cart,Long>, PagingAndSortingRepository<Cart,Long> {
}
