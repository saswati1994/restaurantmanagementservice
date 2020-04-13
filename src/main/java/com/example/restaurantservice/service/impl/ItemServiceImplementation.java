package com.example.restaurantservice.service.impl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.restaurantservice.model.ItemDto;
import com.example.restaurantservice.model.Item;
import com.example.restaurantservice.repository.ItemRepository;
import com.example.restaurantservice.service.ItemService;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;


@Service
@Transactional
@Slf4j
public class ItemServiceImplementation implements ItemService {

	
	@Autowired
	ItemRepository itemRepository;
	
	/*
	 * service implementation for adding a new item into the Restaurant menu
	 */
	@Override
	public void addItem(ItemDto itemDto) {
		
			Item itemObj=new Item();
			
			LocalDate date = LocalDate.now();
			java.sql.Date sqlDate =java.sql.Date.valueOf(date);
				
			itemObj.setItemName(itemDto.getItemName());
			itemObj.setItemType(itemDto.getItemType());
			itemObj.setItemDesc(itemDto.getItemDesc());
			itemObj.setPrice(itemDto.getPrice());
		    itemObj.setDateAdded(sqlDate);
			    
			itemRepository.save(itemObj);
		
	}
	
	
	/*
	 * service implementation for displaying all the items in the Restaurant menu
	 */
	@Override
	public List<Item> displayAllItems()  {
		
		List<Item> items=itemRepository.findAll();		
		return items;
	}
	

	/*
	 * service implementation for updating an item given its id 
	 */
	@Override
	public void updateItem(long id,ItemDto itemDto){
		
		
		Optional<Item> itemObj=itemRepository.findById(id);		
		Item item=itemObj.get();
		
		if(itemDto.getItemName() != null)
			item.setItemName(itemDto.getItemName());
		
		if(itemDto.getItemDesc()!=null)
			item.setItemDesc(itemDto.getItemDesc());
		
		if(itemDto.getPrice()!=null)
			item.setPrice(itemDto.getPrice());
		
		if(itemDto.getItemType()!=null)
				item.setItemType(itemDto.getItemType());
		
		itemRepository.save(item);
		
	}


	/*
	 * service implementation for deleting an item given its id 
	 */
	@Override
	public void deleteItem(long id)  {
		
		itemRepository.deleteById(id);
		
	}

}
