package com.javanauta.revisaoagendadortarefas.business.mapper;

import com.javanauta.revisaoagendadortarefas.business.dtos.TarefaDTORecord;
import com.javanauta.revisaoagendadortarefas.infrastructure.entity.TarefaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateConverter {

    void updateTarefa(TarefaDTORecord dto, @MappingTarget TarefaEntity entity);
}
