package com.project.khob.services.impl;

import com.project.khob.domain.entities.Item;
import com.project.khob.repositories.ItemRepository;
import com.project.khob.services.ItemService;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item CreateItem(Item item) {
        return itemRepository.save(item);
    }
}
