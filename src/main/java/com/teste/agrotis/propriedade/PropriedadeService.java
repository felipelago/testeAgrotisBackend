package com.teste.agrotis.propriedade;

import com.teste.agrotis.propriedade.dto.request.PropriedadeCadastroRequest;
import com.teste.agrotis.propriedade.dto.response.PropriedadeCadastroResponse;
import com.teste.agrotis.propriedade.model.Propriedade;
import org.springframework.stereotype.Service;

@Service
public class PropriedadeService {

    private final PropriedadeAdapter adapter;

    public PropriedadeService(PropriedadeAdapter adapter) {
        this.adapter = adapter;
    }

    public PropriedadeCadastroResponse cadastrarPropriedade(PropriedadeCadastroRequest request) {

        Propriedade propriedade = new Propriedade();
        propriedade.setNome(request.nome());
        propriedade.setAtivo(true);

        return adapter.cadastrarPropriedade(propriedade);
    }
}
