package com.javanauta.revisaoagendadortarefas.business;

import com.javanauta.revisaoagendadortarefas.business.dtos.TarefaDTO;
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

    public TarefaDTO gravaTarefa(String token, TarefaDTO dto) {
        String email = jwtUtil.extractUsername(token.substring(7));
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        dto.setEmailUsuario(email);
        TarefaEntity entity = tarefaConverter.paraTarefaEntity(dto);
        return tarefaConverter.paraTarefaDTO(tarefaRepository.save(entity));
    }

    public List<TarefaDTO> buscaTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return tarefaConverter.paraListaTarefaDTO(
                tarefaRepository.findByDataEventoBetween(dataInicial, dataFinal));
    }

    public List<TarefaDTO> buscaTarefasPorEmail(String token) {
        String email = jwtUtil.extractUsername(token.substring(7));
        return tarefaConverter.paraListaTarefaDTO(
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

    public TarefaDTO alteraStatusNotificacao(StatusNotificacaoEnum status, String id) {
        try {
            TarefaEntity entity = tarefaRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("Tarefa não encontrada " + id));
            entity.setStatusNotificacaoEnum(status);
            return tarefaConverter.paraTarefaDTO(tarefaRepository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa" + id, e.getCause());
        }
    }

    public TarefaDTO updateTarefa(TarefaDTO dto, String id) {
        try {
            TarefaEntity entity = tarefaRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("Tarefa não encontrada " + id));
            tarefaUpdateConverter.updateTarefa(dto, entity);
            return tarefaConverter.paraTarefaDTO(tarefaRepository.save(entity));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Tarefa não encontrada " + id, e.getCause());
        }
    }
}
