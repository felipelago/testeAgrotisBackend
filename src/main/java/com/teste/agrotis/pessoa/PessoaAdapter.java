package com.teste.agrotis.pessoa;

import com.teste.agrotis.pessoa.dto.response.PessoaCadastroResponse;
import com.teste.agrotis.pessoa.model.Pessoa;
import org.springframework.stereotype.Repository;

@Repository
public class PessoaAdapter {

    private final PessoaRepository pessoaRepository;

    public PessoaAdapter(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public PessoaCadastroResponse salvarPessoa(Pessoa pessoa) {
        Pessoa saved = pessoaRepository.save(pessoa);

        return new PessoaCadastroResponse(
                saved.getNome(),
                saved.getDataInicial(),
                saved.getDataFinal(),
                saved.getObservacoes(),
                new PessoaCadastroResponse.PropriedadeDTO(saved.getPropriedade().getNome()),
                new PessoaCadastroResponse.LaboratorioDTO(saved.getLaboratorio().getNome())
        );
    }
}