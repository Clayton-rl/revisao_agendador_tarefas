package com.javanauta.revisaoagendadortarefas.business.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javanauta.revisaoagendadortarefas.infrastructure.enums.StatusNotificacaoEnum;

import java.time.LocalDateTime;

public record TarefaDTORecord(String id,
                              String nomeTarefa,
                              String descricao,
                              String emailUsuario,
                              @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
                              LocalDateTime dataCriacao,
                              @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
                              LocalDateTime dataEvento,
                              @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
                              LocalDateTime dataAlteracao,
                              StatusNotificacaoEnum statusNotificacaoEnum
) {
}
