package com.teste.agrotis.propriedade;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teste.agrotis.propriedade.dto.response.PropriedadeCadastroResponse;
import com.teste.agrotis.propriedade.dto.response.PropriedadeListarResponse;
import com.teste.agrotis.propriedade.model.Propriedade;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PropriedadeAdapter {

    private final PropriedadeRepository propriedadeRepository;
    private final ObjectMapper objectMapper;

    public PropriedadeAdapter(PropriedadeRepository propriedadeRepository, ObjectMapper objectMapper) {
        this.propriedadeRepository = propriedadeRepository;
        this.objectMapper = objectMapper;
    }

    public PropriedadeCadastroResponse salvarPropriedade(Propriedade propriedade) {
        Propriedade saved = propriedadeRepository.save(propriedade);
        return objectMapper.convertValue(saved, PropriedadeCadastroResponse.class);
    }

    public boolean existePorNome(String nome) {
        return propriedadeRepository.existsByNome(nome);
    }

    public List<PropriedadeListarResponse> listarPropriedades() {
        return propriedadeRepository.findAll().stream()
                .map(lab -> objectMapper.convertValue(lab, PropriedadeListarResponse.class))
                .toList();
    }

    public boolean existePorId(Long id) {
        return propriedadeRepository.existsById(id);
    }

    public boolean temPessoasVinculadas(Long propriedadeId) {
        return propriedadeRepository.countPessoasById(propriedadeId) > 0;
    }

    public void deletarPropriedade(Long id) {
        propriedadeRepository.deleteById(id);
    }
}
