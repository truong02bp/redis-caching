package com.redis.rediscaching.data.repositories;

import com.redis.rediscaching.data.document.GroceryItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface GroceryItemRepository extends MongoRepository<GroceryItem, String> {

    @Query(value = "{category : '?0'}", fields = "{name: 1, quantity: 1}")
    List<GroceryItem> getByCategory(String category);

    int countByCategory(String category);
}
