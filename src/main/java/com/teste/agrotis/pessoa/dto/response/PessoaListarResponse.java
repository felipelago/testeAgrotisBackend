package com.teste.agrotis.pessoa.dto.response;

import java.time.LocalDateTime;

public record PessoaListarResponse(
        String nome,
        LocalDateTime dataInicial,
        LocalDateTime dataFinal,
        String observacoes,
        PropriedadeDTO propriedade,
        LaboratorioDTO laboratorio
) {
    public record PropriedadeDTO(String nome) {
    }

    public record LaboratorioDTO(String nome) {
    }
}
