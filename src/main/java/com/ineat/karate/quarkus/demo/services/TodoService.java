package com.ineat.karate.quarkus.demo.services;

import com.ineat.karate.quarkus.demo.dtos.TodoDTO;
import com.ineat.karate.quarkus.demo.repositories.TodoPanacheRepository;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TodoService {

    @Inject
    TodoPanacheRepository todoPanacheRepository;

    public List<TodoDTO> find(Optional<String> maybeTitle) {
        return maybeTitle
                .map(title -> todoPanacheRepository.findByTitle(title))
                .orElseGet(() -> todoPanacheRepository.all());
    }

    public TodoDTO getById(String id) {
        return todoPanacheRepository.get(id);
    }

    public String create(TodoDTO todoDTO) {
        return todoPanacheRepository.create(todoDTO);
    }

    public boolean delete(String id) {
        return todoPanacheRepository.delete(id);
    }

}
