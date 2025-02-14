package com.example.codersquare.mapper;

public interface Mapper<Entity, ReqDTO, ResDTO> {
    Entity mapToEntity(ReqDTO reqDTO);

    ResDTO mapToResponse(Entity entity);
}
