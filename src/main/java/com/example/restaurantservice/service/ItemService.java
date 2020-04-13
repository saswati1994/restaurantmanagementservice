package com.example.restaurantservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.restaurantservice.model.ItemDto;
import com.example.restaurantservice.model.Item;

@Service
public interface ItemService {

	public void addItem(ItemDto itemDto);

	public List<Item> displayAllItems() ;

	public void updateItem(long id, ItemDto itemDto);

	public void deleteItem(long id) ;

}
