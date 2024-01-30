package com.project.khob.repositories;

import com.project.khob.domain.entities.Item;
import com.project.khob.domain.entities.ItemKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository  extends CrudRepository<Item, ItemKey>, PagingAndSortingRepository<Item, ItemKey> {
}
