package com.javanauta.revisaoagendadortarefas.controller;

import com.javanauta.revisaoagendadortarefas.business.TarefaService;
import com.javanauta.revisaoagendadortarefas.business.dtos.TarefaDTO;
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
    public ResponseEntity<TarefaDTO> gravaTarefa(@RequestBody TarefaDTO dto,
                                                 @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefaService.gravaTarefa(token, dto));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefaDTO>> buscaTarefasAgendadasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal) {
        return ResponseEntity.ok(tarefaService.buscaTarefasAgendadasPorPeriodo(dataInicial, dataFinal));
    }

    @GetMapping
    public ResponseEntity<List<TarefaDTO>> buscaTarefasPorEmail(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefaService.buscaTarefasPorEmail(token));
    }
}
