package com.javanauta.revisaoagendadortarefas.infrastructure.repository;

import com.javanauta.revisaoagendadortarefas.infrastructure.entity.TarefaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends MongoRepository<TarefaEntity, String> {


}
