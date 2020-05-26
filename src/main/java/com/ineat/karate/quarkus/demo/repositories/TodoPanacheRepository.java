package com.ineat.karate.quarkus.demo.repositories;

import com.ineat.karate.quarkus.demo.dtos.TodoDTO;
import com.ineat.karate.quarkus.demo.entities.TodoEntity;
import com.ineat.karate.quarkus.demo.mappers.TodoMapper;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class TodoPanacheRepository implements PanacheMongoRepository<TodoEntity> {

    @Inject
    TodoMapper todoMapper;

    public List<TodoDTO> all() {
        return todoMapper.toDTOs(listAll());
    }

    public List<TodoDTO> findByTitle(String title) {
        return todoMapper.toDTOs(find("title = ?1", title).list());
    }

    public TodoDTO get(String id) {
        return todoMapper.toDTO(findById(new ObjectId(id)));
    }

    public String create(TodoDTO todoDTO) {
        TodoEntity todoEntity = todoMapper.toEntity(todoDTO);
        persist(todoEntity);
        return todoEntity.id.toString();
    }

    public boolean delete(String id) {
        return deleteById(new ObjectId(id));
    }

}
