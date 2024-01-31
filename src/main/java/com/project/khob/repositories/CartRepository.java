package com.project.khob.repositories;

import com.project.khob.domain.entities.Cart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends CrudRepository<Cart,Long>, PagingAndSortingRepository<Cart,Long> {

    @Query(value = "SELECT * FROM cart WHERE user_id = ?1 AND date_closed IS NOT NULL ORDER BY date_created DESC LIMIT 1", nativeQuery = true)
    Optional<Cart> findByUserId(Long userId);
}
