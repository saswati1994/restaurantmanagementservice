package com.example.restaurantservice.controller;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.restaurantservice.exception.ItemNotFoundException;
import com.example.restaurantservice.model.Item;
import com.example.restaurantservice.model.ItemDto;
import com.example.restaurantservice.repository.ItemRepository;
import com.example.restaurantservice.service.ItemService;
/*import com.eatfit.restaurant.dto.ItemDto;
import com.eatfit.restaurant.entity.Item;
import com.eatfit.restaurant.exception.ItemNotFoundException;
import com.eatfit.restaurant.repository.ItemRepository;
import com.eatfit.restaurant.service.ItemService;*/
import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;




@RestController
@Slf4j
@CrossOrigin(origins="*")
@RequestMapping("/items")
public class ItemController {

	@Autowired
	ItemService itemService;
	
	@Autowired
	ItemRepository itemRepository;
	
	
	/*
	 * adding a new item into the restaurant menu
	 * 
	 * syntax: format for adding the itemType is: VEG/NONVEG/VEGAN
	 */
	
	@PostMapping("/item")
	public ResponseEntity<?> addItem(@RequestBody ItemDto itemDto) {
		
			itemService.addItem(itemDto);
			log.debug("Adding new Item");
			return ResponseEntity.status(HttpStatus.OK).body("Item added successfully");
			
	}
	
	/*
	 * displaying all the items in the menu
	 */
	@GetMapping("/")
	public ResponseEntity<List<Item>> displayAllItems() {
		
		List<Item> items=itemService.displayAllItems();
		if(items.isEmpty())
			throw new ItemNotFoundException("no items in the menu");
		log.debug("Fetching All Items");
		return new ResponseEntity<List<Item>>(items,HttpStatus.OK);
		
	}
	
	/*
	 * displaying an item using item id
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Item> displayItemById(@PathVariable long id) {
	
		Optional<Item> item=itemRepository.findById(id);
		if(!item.isPresent()) 
		{
			log.debug("Item Not found");
			throw new ItemNotFoundException("Item Not Found for Id: " + id);
		}
		return new ResponseEntity<Item>(item.get(),HttpStatus.OK);
		
	}
		
		/*
		 * updating an item using item id
		 * 
		 * syntax: format for adding the itemType is: VEG/NONVEG/VEGAN
		 */
		@PutMapping("/item/{id}")
		public ResponseEntity<?> updateItem(@PathVariable long id, @RequestBody ItemDto itemDto) {
			
			Optional<Item> item=itemRepository.findById(id);
			if(!item.isPresent())
			{
				log.debug("Item Not found");
				throw new ItemNotFoundException("invalid item id "+id);
			}
			itemService.updateItem(id, itemDto);
			return ResponseEntity.status(HttpStatus.OK).body("Item updated successfully for id: "+ id);
	}
		
		/*
		 * deleting an item using item id
		 */

		@DeleteMapping("/item/{id}")
		public ResponseEntity<?> deleteItem(@PathVariable long id) {
			Optional<Item> item=itemRepository.findById(id);
			if(!item.isPresent()) {
				log.debug("Item Not found");
				throw new ItemNotFoundException("invalid item id "+id);
			}
			itemService.deleteItem(id);
			return ResponseEntity.status(HttpStatus.OK).body("Item deleted successfully for id: "+ id);
		}
		
		/*
		 * to handle invalid input type 
		 */
		@ExceptionHandler(HttpMessageNotReadableException.class)
		@ResponseBody
		public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex)
		{
		    JsonMappingException jme = (JsonMappingException) ex.getCause();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("exceptionMessage: "+ jme.getPath().get(0).getFieldName() + " invalid");
		}
}
