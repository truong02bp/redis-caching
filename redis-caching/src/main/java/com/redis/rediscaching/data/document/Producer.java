package com.redis.rediscaching.data.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "producer")
@Getter
@Setter
public class Producer implements Serializable {
    @Id
    private String id;
    private String name;
    private String country;
}
