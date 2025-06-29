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

    public PropriedadeCadastroResponse cadastrarPropriedade(Propriedade propriedade) {
        Propriedade response = propriedadeRepository.save(propriedade);
        return objectMapper.convertValue(response, PropriedadeCadastroResponse.class);
    }

//    public List<PropriedadeListarResponse> listarPropriedades() {
//        Propriedade response = propriedadeRepository.findAll();
//        return
//    }
}
