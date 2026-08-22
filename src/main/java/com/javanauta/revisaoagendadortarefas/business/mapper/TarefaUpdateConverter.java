package com.javanauta.revisaoagendadortarefas.business.mapper;

import com.javanauta.revisaoagendadortarefas.business.dtos.TarefaDTO;
import com.javanauta.revisaoagendadortarefas.infrastructure.entity.TarefaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateConverter {

    void updateTarefa(TarefaDTO dto, @MappingTarget TarefaEntity entity);
}
