package com.redis.rediscaching.data.repositories;

import com.redis.rediscaching.data.document.Producer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProducerRepository extends MongoRepository<Producer, String> {
}
