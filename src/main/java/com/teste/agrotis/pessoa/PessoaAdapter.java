package com.teste.agrotis.pessoa;

import com.teste.agrotis.laboratorio.LaboratorioRepository;
import com.teste.agrotis.laboratorio.model.Laboratorio;
import com.teste.agrotis.pessoa.model.Pessoa;
import com.teste.agrotis.propriedade.PropriedadeRepository;
import com.teste.agrotis.propriedade.model.Propriedade;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PessoaAdapter {

    private final PessoaRepository pessoaRepository;
    private final PropriedadeRepository propriedadeRepository;
    private final LaboratorioRepository laboratorioRepository;

    public PessoaAdapter(PessoaRepository pessoaRepository, PropriedadeRepository propriedadeRepository, LaboratorioRepository laboratorioRepository) {
        this.pessoaRepository = pessoaRepository;
        this.propriedadeRepository = propriedadeRepository;
        this.laboratorioRepository = laboratorioRepository;
    }

    public Pessoa salvarPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Propriedade buscarPropriedadePorId(Long id) {
        return propriedadeRepository.findById(id)
                .orElse(null);
    }

    public Laboratorio buscarLaboratorioPorId(Long id) {
        return laboratorioRepository.findById(id)
                .orElse(null);
    }

    public List<Pessoa> listarPessoas() {
        return pessoaRepository.findAllWithRelationships();
    }
}