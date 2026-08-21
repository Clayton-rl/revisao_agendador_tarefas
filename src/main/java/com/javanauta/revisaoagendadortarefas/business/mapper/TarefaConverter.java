package com.javanauta.revisaoagendadortarefas.business.mapper;

import com.javanauta.revisaoagendadortarefas.business.dtos.TarefaDTO;
import com.javanauta.revisaoagendadortarefas.infrastructure.entity.TarefaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefaConverter {

    TarefaEntity paraTarefaEntity(TarefaDTO dto);

    TarefaDTO paraTarefaDTO(TarefaEntity entity);
}
