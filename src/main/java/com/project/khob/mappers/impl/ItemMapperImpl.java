package com.project.khob.mappers.impl;

import com.project.khob.domain.dto.ItemDto;
import com.project.khob.domain.entities.Item;
import com.project.khob.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component

public class ItemMapperImpl implements Mapper<Item, ItemDto> {
    private ModelMapper modelMapper;

    public ItemMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ItemDto mapTo(Item item) {
        return modelMapper.map(item, ItemDto.class);
    }

    @Override
    public Item mapFrom(ItemDto itemDto) {
        return modelMapper.map(itemDto, Item.class);
    }
}
