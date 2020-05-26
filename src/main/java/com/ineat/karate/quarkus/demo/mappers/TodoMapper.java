package com.ineat.karate.quarkus.demo.mappers;

import com.ineat.karate.quarkus.demo.dtos.TodoDTO;
import com.ineat.karate.quarkus.demo.entities.TodoEntity;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "cdi")
public interface TodoMapper {

    @Mappings({
            @Mapping(source = "id", target = "id", qualifiedByName = "stringToObjectId"),
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "priority", target = "priority")
    })
    TodoEntity toEntity(TodoDTO todoDTO);

    @Named("stringToObjectId")
    public static ObjectId stringToObjectId(String id) {
        return Optional.ofNullable(id).map(_id -> new ObjectId(_id)).orElse(new ObjectId());
    }

    @Mappings({
            @Mapping(source = "id", target = "id", qualifiedByName = "objectIdToString"),
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "priority", target = "priority")
    })
    TodoDTO toDTO(TodoEntity todoEntity);

    @Named("stringToObjectId")
    public static String objectIdToString(ObjectId id) {
        return id.toString();
    }

    List<TodoDTO> toDTOs(List<TodoEntity> todoEntities);
}
