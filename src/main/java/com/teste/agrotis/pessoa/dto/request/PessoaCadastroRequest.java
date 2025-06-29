package com.teste.agrotis.pessoa.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record PessoaCadastroRequest(
        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
        String nome,

        @NotNull(message = "Data inicial é obrigatória")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
        LocalDateTime dataInicial,

        @NotNull(message = "Data final é obrigatória")
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
        LocalDateTime dataFinal,

        @NotNull(message = "Informações da propriedade são obrigatórias")
        @Valid
        InfosPropriedadeRequest infosPropriedade,

        @NotNull(message = "Laboratório é obrigatório")
        @Valid
        LaboratorioRequest laboratorio,

        @Size(max = 1000, message = "Observações deve ter no máximo 1000 caracteres")
        String observacoes
) {
    public record InfosPropriedadeRequest(
            @NotNull(message = "ID da propriedade é obrigatório")
            Long id,
            String nome
    ) {
    }

    public record LaboratorioRequest(
            @NotNull(message = "ID do laboratório é obrigatório")
            Long id,
            String nome
    ) {
    }
}
