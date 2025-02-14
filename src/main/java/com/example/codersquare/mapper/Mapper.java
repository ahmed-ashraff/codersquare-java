package com.example.codersquare.mapper;

public interface Mapper<Entity, EntityDto> {
    Entity mapToEntity(EntityDto entityDTO);

    EntityDto mapToDTO(Entity entity);
}
