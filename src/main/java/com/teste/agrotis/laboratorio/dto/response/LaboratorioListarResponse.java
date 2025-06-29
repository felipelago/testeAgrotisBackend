package com.teste.agrotis.laboratorio.dto.response;

import com.teste.agrotis.pessoa.dto.response.PessoaListarResponse;

import java.util.List;

public record LaboratorioListarResponse(
        Long id,
        String nome,
        List<PessoaListarResponse> pessoas
) {
}
