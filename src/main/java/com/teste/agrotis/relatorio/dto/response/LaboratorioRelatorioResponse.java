package com.teste.agrotis.relatorio.dto.response;

public record LaboratorioRelatorioResponse(
        Long codigo,
        String nome,
        Long quantidadePessoas
) {
    //Construtor para retornar todos os nomes dos laboratorios em maiusculo
    public LaboratorioRelatorioResponse(Long codigo, String nome, Long quantidadePessoas) {
        this.codigo = codigo;
        this.nome = nome != null ? nome.toUpperCase() : null;
        this.quantidadePessoas = quantidadePessoas;
    }
}
