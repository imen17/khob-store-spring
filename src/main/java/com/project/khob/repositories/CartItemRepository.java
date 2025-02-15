package com.project.khob.repositories;

import com.project.khob.domain.dto.CartDTO;
import com.project.khob.domain.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {
    List<CartItem> getActiveItemsByCartId(Long cartId);
}
