package com.project.khob.services;

import com.project.khob.domain.entities.Item;
import org.springframework.stereotype.Service;

@Service
public interface ItemService {
    Item CreateItem(Item item);
}
