package com.ineat.karate.quarkus.demo.entities;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection = "todo")
public class TodoEntity {
    public ObjectId id;
    public String title;
    public String description;
    public Integer priority;
}