package com.teste.agrotis.propriedade.dto.response;

import com.teste.agrotis.pessoa.dto.response.PessoaListarResponse;

import java.util.List;

public record PropriedadeListarResponse(
        Long id,
        String nome,
        Boolean ativo,
        List<PessoaListarResponse> pessoas
) {
}
