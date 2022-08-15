package com.redis.rediscaching.services;

import com.redis.rediscaching.data.document.GroceryItem;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface GroceryItemService {

    List<GroceryItem> getAll();

    List<GroceryItem> getByCategory(String category);

    Integer countByCategory(String category);

    GroceryItem findById(String id);

    GroceryItem update(GroceryItem groceryItem);

    GroceryItem updateProducer(String id, String producerId);

    GroceryItem create(GroceryItem groceryItem);

    void deleteById(String id);
}
