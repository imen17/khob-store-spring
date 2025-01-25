package com.project.khob.repositories;

import com.project.khob.domain.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    Optional<Cart> getNewestCartByUserId(Long userId);


}
