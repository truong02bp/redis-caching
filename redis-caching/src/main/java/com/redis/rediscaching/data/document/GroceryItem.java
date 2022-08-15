package com.redis.rediscaching.data.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document("grocery_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroceryItem implements Serializable {

    @Id
    private String id;
    private String name;
    private int quantity;
    private String category;
    private Producer producer;
}