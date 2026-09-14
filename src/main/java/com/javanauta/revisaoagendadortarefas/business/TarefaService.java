package com.javanauta.revisaoagendadortarefas.business;

import com.javanauta.revisaoagendadortarefas.business.dtos.TarefaDTORecord;
import com.javanauta.revisaoagendadortarefas.business.mapper.TarefaConverter;
import com.javanauta.revisaoagendadortarefas.business.mapper.TarefaUpdateConverter;
import com.javanauta.revisaoagendadortarefas.infrastructure.entity.TarefaEntity;
import com.javanauta.revisaoagendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.javanauta.revisaoagendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.javanauta.revisaoagendadortarefas.infrastructure.repository.TarefaRepository;
import com.javanauta.revisaoagendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaConverter tarefaConverter;
    private final JwtUtil jwtUtil;
    private final TarefaUpdateConverter tarefaUpdateConverter;

    public TarefaDTORecord gravaTarefa(String token, TarefaDTORecord dto) {
        String email = jwtUtil.extractUsername(token.substring(7));
        TarefaDTORecord dtoFinal = new TarefaDTORecord(null, dto.nomeTarefa(), dto.descricao(), email,
                LocalDateTime.now(), dto.dataEvento(), null, StatusNotificacaoEnum.PENDENTE);
        TarefaEntity entity = tarefaConverter.paraTarefaEntity(dtoFinal);
        return tarefaConverter.paraTarefaDTORecord(tarefaRepository.save(entity));
    }

    public List<TarefaDTORecord> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return tarefaConverter.paraListaTarefaDTORecord(
                tarefaRepository.findByDataEventoBetweenAndStatusNotificacaoEnum(
                        dataInicial, dataFinal, StatusNotificacaoEnum.PENDENTE));
    }

    public List<TarefaDTORecord> buscaTarefasPorEmail(String token) {
        String email = jwtUtil.extractUsername(token.substring(7));
        return tarefaConverter.paraListaTarefaDTORecord(
                tarefaRepository.findByEmailUsuario(email));
    }

    public void deletaTarefaPorId(String id) {
        try {
            tarefaRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao deletar tarefa por id, id inexistente " + id,
                    e.getCause());
        }
    }

    public TarefaDTORecord alteraStatusNotificacao(StatusNotificacaoEnum status, String id) {
        try {
            TarefaEntity entity = tarefaRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("Tarefa não encontrada " + id));
            entity.setStatusNotificacaoEnum(status);
            return tarefaConverter.paraTarefaDTORecord(tarefaRepository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa" + id, e.getCause());
        }
    }

    public TarefaDTORecord updateTarefa(TarefaDTORecord dto, String id) {
        try {
            TarefaEntity entity = tarefaRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("Tarefa não encontrada " + id));
            tarefaUpdateConverter.updateTarefa(dto, entity);
            return tarefaConverter.paraTarefaDTORecord(tarefaRepository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Tarefa não encontrada " + id, e.getCause());
        }
    }
}
