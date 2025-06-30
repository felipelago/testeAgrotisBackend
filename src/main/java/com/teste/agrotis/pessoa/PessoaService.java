package com.teste.agrotis.pessoa;

import com.teste.agrotis.exception.ResourceNotFoundException;
import com.teste.agrotis.laboratorio.LaboratorioRepository;
import com.teste.agrotis.laboratorio.model.Laboratorio;
import com.teste.agrotis.pessoa.dto.request.PessoaCadastroRequest;
import com.teste.agrotis.pessoa.dto.response.PessoaCadastroResponse;
import com.teste.agrotis.pessoa.model.Pessoa;
import com.teste.agrotis.propriedade.PropriedadeRepository;
import com.teste.agrotis.propriedade.model.Propriedade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PessoaService {

    private final PessoaAdapter pessoaAdapter;
    private final LaboratorioRepository laboratorioRepository;
    private final PropriedadeRepository propriedadeRepository;

    public PessoaService(PessoaAdapter pessoaAdapter, LaboratorioRepository laboratorioRepository, PropriedadeRepository propriedadeRepository) {
        this.pessoaAdapter = pessoaAdapter;
        this.laboratorioRepository = laboratorioRepository;
        this.propriedadeRepository = propriedadeRepository;
    }

    public PessoaCadastroResponse salvarPessoa(PessoaCadastroRequest request) {

        Propriedade propriedade = propriedadeRepository.findById(request.infosPropriedade().id())
                .orElseThrow(() -> new ResourceNotFoundException("Propriedade não encontrada"));

        Laboratorio laboratorio = laboratorioRepository.findById(request.laboratorio().id())
                .orElseThrow(() -> new ResourceNotFoundException("Laboratório não encontrado"));


        Pessoa pessoa = new Pessoa();
        pessoa.setNome(request.nome());
        pessoa.setDataInicial(request.dataInicial());
        pessoa.setDataFinal(request.dataFinal());
        pessoa.setLaboratorio(laboratorio);
        pessoa.setPropriedade(propriedade);
        pessoa.setObservacoes(request.observacoes());

        return pessoaAdapter.salvarPessoa(pessoa);
    }
}
