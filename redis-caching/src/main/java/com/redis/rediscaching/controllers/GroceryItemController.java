package com.redis.rediscaching.controllers;

import com.redis.rediscaching.data.document.GroceryItem;
import com.redis.rediscaching.services.GroceryItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/grocery-item")
@RequiredArgsConstructor
public class GroceryItemController {

    private final GroceryItemService groceryItemService;

    @GetMapping
    public ResponseEntity<List<GroceryItem>> getAll() {
        return ResponseEntity.ok(groceryItemService.getAll());
    }

    @GetMapping("/category")
    public ResponseEntity<List<GroceryItem>> getByCategory(@RequestParam(name = "category") String category) {
        return ResponseEntity.ok(groceryItemService.getByCategory(category));
    }

    @GetMapping("/category/count")
    public ResponseEntity<Integer> countByCategory(@RequestParam(name = "category") String category) {
        return ResponseEntity.ok(groceryItemService.countByCategory(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroceryItem> findById(@PathVariable String id) {
        return ResponseEntity.ok(groceryItemService.findById(id));
    }

    @PostMapping
    public ResponseEntity<GroceryItem> create(@RequestBody GroceryItem groceryItem) {
        return ResponseEntity.ok(groceryItemService.create(groceryItem));
    }

    @PutMapping
    public ResponseEntity<GroceryItem> update(@RequestBody GroceryItem groceryItem) {
        return ResponseEntity.ok(groceryItemService.update(groceryItem));
    }

    @PutMapping("/producer")
    public ResponseEntity<GroceryItem> updateProducer(@RequestParam("id") String id, @RequestParam("producerId") String producerId) {
        return ResponseEntity.ok(groceryItemService.updateProducer(id, producerId));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        groceryItemService.deleteById(id);
    }

}
