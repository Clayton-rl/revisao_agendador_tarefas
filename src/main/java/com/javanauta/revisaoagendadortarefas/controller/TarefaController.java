package com.javanauta.revisaoagendadortarefas.controller;

import com.javanauta.revisaoagendadortarefas.business.TarefaService;
import com.javanauta.revisaoagendadortarefas.business.dtos.TarefaDTORecord;
import com.javanauta.revisaoagendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefa")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaDTORecord> gravaTarefa(@RequestBody TarefaDTORecord dto,
                                                 @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefaService.gravaTarefa(token, dto));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefaDTORecord>> buscaTarefasAgendadasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal) {
        return ResponseEntity.ok(tarefaService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal));
    }

    @GetMapping
    public ResponseEntity<List<TarefaDTORecord>> buscaTarefasPorEmail(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefaService.buscaTarefasPorEmail(token));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletaTarefaPorId(@RequestParam("id") String id) {
        tarefaService.deletaTarefaPorId(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<TarefaDTORecord> alteraStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                                             @RequestParam("id") String id) {
        return ResponseEntity.ok(tarefaService.alteraStatusNotificacao(status, id));
    }

    @PutMapping
    public ResponseEntity<TarefaDTORecord> updateTarefa(@RequestBody TarefaDTORecord dto,
                                                  @RequestParam("id") String id) {
        return ResponseEntity.ok(tarefaService.updateTarefa(dto, id));
    }
}
