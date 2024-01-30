package com.project.khob.controllers;

import com.project.khob.domain.dto.ItemDto;
import com.project.khob.domain.entities.Item;
import com.project.khob.mappers.Mapper;
import com.project.khob.services.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {
    private ItemService itemService;

    private Mapper<Item, ItemDto> itemMapper;

    public ItemController(ItemService itemService, Mapper<Item, ItemDto> itemMapper) {
        this.itemService = itemService;
        this.itemMapper = itemMapper;
    }

    @PostMapping(path = "auth/items/new")
    public ResponseEntity<ItemDto> createItem(@RequestBody ItemDto itemDto){
        Item item=itemMapper.mapFrom(itemDto);
        Item savedItem = itemService.CreateItem(item);
        return new ResponseEntity<>(itemMapper.mapTo(savedItem), HttpStatus.CREATED);
    }
}
