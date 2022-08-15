package com.redis.rediscaching.services.impl;

import com.mongodb.client.result.UpdateResult;
import com.redis.rediscaching.data.document.GroceryItem;
import com.redis.rediscaching.data.repositories.GroceryItemRepository;
import com.redis.rediscaching.data.repositories.ProducerRepository;
import com.redis.rediscaching.services.GroceryItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class GroceryItemServiceImpl implements GroceryItemService {

    private final GroceryItemRepository groceryItemRepository;
    private final MongoTemplate mongoTemplate;
    private final ProducerRepository producerRepository;

    @Override
    public List<GroceryItem> getAll() {
        log.info("Query from database...");
        return groceryItemRepository.findAll();
    }

    @Override
    public List<GroceryItem> getByCategory(String category) {
        return groceryItemRepository.getByCategory(category);
    }

    @Override
    public Integer countByCategory(String category) {
        return groceryItemRepository.countByCategory(category);
    }

    @Override
    @Cacheable(value = "grocery-item", key = "#id")
    public GroceryItem findById(String id) {
        log.info("Query from database...");
        return groceryItemRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Can't find with id: %s".formatted(id));
        });
    }

    @Override
    @CachePut(value = "grocery-item", key = "#groceryItem.id")
    public GroceryItem update(GroceryItem groceryItem) {
        return groceryItemRepository.save(groceryItem);
    }

    @Override
    @CachePut(value = "grocery-item", key = "#id")
    public GroceryItem updateProducer(String id, String producerId) {
        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update();
        update.set("producer", producerRepository.findById(producerId).orElse(null));
        mongoTemplate.updateFirst(query, update, GroceryItem.class);
        return mongoTemplate.findById(id, GroceryItem.class);
    }

    @Override
    public GroceryItem create(GroceryItem groceryItem) {
        return groceryItemRepository.save(groceryItem);
    }

    @Override
    @CacheEvict(value = "grocery-item", key = "#id", allEntries = true)
    public void deleteById(String id) {
        groceryItemRepository.deleteById(id);
    }
}
