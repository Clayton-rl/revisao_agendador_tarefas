package com.javanauta.revisaoagendadortarefas.business.mapper;

import com.javanauta.revisaoagendadortarefas.business.dtos.TarefaDTORecord;
import com.javanauta.revisaoagendadortarefas.infrastructure.entity.TarefaEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefaConverter {

    TarefaEntity paraTarefaEntity(TarefaDTORecord dto);

    TarefaDTORecord paraTarefaDTORecord(TarefaEntity entity);

    List<TarefaEntity> paraListaTarefaEntity(List<TarefaDTORecord> dtos);

    List<TarefaDTORecord> paraListaTarefaDTORecord(List<TarefaEntity> entities);

}
