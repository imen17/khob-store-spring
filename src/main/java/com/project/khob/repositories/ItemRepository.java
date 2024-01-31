package com.project.khob.repositories;

import com.project.khob.domain.entities.CartItem;
import com.project.khob.domain.entities.CartItemKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository  extends CrudRepository<CartItem, CartItemKey>, PagingAndSortingRepository<CartItem, CartItemKey> {
}
