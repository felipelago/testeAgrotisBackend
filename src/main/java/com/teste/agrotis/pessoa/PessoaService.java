package com.teste.agrotis.pessoa;

import com.teste.agrotis.exception.ResourceNotFoundException;
import com.teste.agrotis.laboratorio.model.Laboratorio;
import com.teste.agrotis.pessoa.dto.request.PessoaCadastroRequest;
import com.teste.agrotis.pessoa.dto.response.PessoaCadastroResponse;
import com.teste.agrotis.pessoa.dto.response.PessoaListarResponse;
import com.teste.agrotis.pessoa.model.Pessoa;
import com.teste.agrotis.propriedade.model.Propriedade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PessoaService {

    private final PessoaAdapter pessoaAdapter;

    public PessoaService(PessoaAdapter pessoaAdapter) {
        this.pessoaAdapter = pessoaAdapter;
    }

    public PessoaCadastroResponse salvarPessoa(PessoaCadastroRequest request) {

        Propriedade propriedade = pessoaAdapter.buscarPropriedadePorId(request.infosPropriedade().id());
        if (propriedade == null) {
            throw new ResourceNotFoundException("Propriedade não encontrada");
        }

        Laboratorio laboratorio = pessoaAdapter.buscarLaboratorioPorId(request.laboratorio().id());
        if (laboratorio == null) {
            throw new ResourceNotFoundException("Laboratório não encontrado");
        }

        Pessoa pessoa = criarPessoaAPartirDoRequest(request, propriedade, laboratorio);
        Pessoa pessoaSalva = pessoaAdapter.salvarPessoa(pessoa);

        return mapCadastrarPessoaResponse(pessoaSalva);
    }

    private Pessoa criarPessoaAPartirDoRequest(PessoaCadastroRequest request,
                                               Propriedade propriedade,
                                               Laboratorio laboratorio) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(request.nome());
        pessoa.setDataInicial(request.dataInicial());
        pessoa.setDataFinal(request.dataFinal());
        pessoa.setLaboratorio(laboratorio);
        pessoa.setPropriedade(propriedade);
        pessoa.setObservacoes(request.observacoes());
        return pessoa;
    }

    private PessoaCadastroResponse mapCadastrarPessoaResponse(Pessoa pessoa) {
        return new PessoaCadastroResponse(
                pessoa.getNome(),
                pessoa.getDataInicial(),
                pessoa.getDataFinal(),
                pessoa.getObservacoes(),
                new PessoaCadastroResponse.PropriedadeDTO(pessoa.getPropriedade().getNome()),
                new PessoaCadastroResponse.LaboratorioDTO(pessoa.getLaboratorio().getNome())
        );
    }

    public List<PessoaListarResponse> listarPessoas() {
        List<Pessoa> pessoas = pessoaAdapter.listarPessoas();

        return pessoas.stream()
                .map(this::mapListarPessoaResponse)
                .toList();
    }

    private PessoaListarResponse mapListarPessoaResponse(Pessoa pessoa) {
        return new PessoaListarResponse(
                pessoa.getNome(),
                pessoa.getDataInicial(),
                pessoa.getDataFinal(),
                pessoa.getObservacoes(),
                new PessoaListarResponse.PropriedadeDTO(pessoa.getPropriedade().getNome()),
                new PessoaListarResponse.LaboratorioDTO(pessoa.getLaboratorio().getNome())
        );
    }

    public void deletarPessoa(Long id) {
        if (!pessoaAdapter.existePorId(id)) {
            throw new ResourceNotFoundException("Pessoa não encontrada");
        }

        pessoaAdapter.deletarPessoa(id);
    }
}
