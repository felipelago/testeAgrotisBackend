package com.teste.agrotis.propriedade;

import com.teste.agrotis.exception.BusinessException;
import com.teste.agrotis.exception.DuplicateResourceException;
import com.teste.agrotis.exception.ResourceNotFoundException;
import com.teste.agrotis.propriedade.dto.request.PropriedadeCadastroRequest;
import com.teste.agrotis.propriedade.dto.response.PropriedadeCadastroResponse;
import com.teste.agrotis.propriedade.dto.response.PropriedadeListarResponse;
import com.teste.agrotis.propriedade.model.Propriedade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropriedadeService {

    private final PropriedadeAdapter adapter;

    public PropriedadeService(PropriedadeAdapter adapter) {
        this.adapter = adapter;
    }

    public PropriedadeCadastroResponse cadastrarPropriedade(PropriedadeCadastroRequest request) {

        if (adapter.existePorNome(request.nome())) {
            throw new DuplicateResourceException("Já existe uma propriedade com este nome");
        }

        Propriedade propriedade = new Propriedade();
        propriedade.setNome(request.nome());
        propriedade.setAtivo(true);

        return adapter.salvarPropriedade(propriedade);
    }

    public List<PropriedadeListarResponse> listarPropriedades() {
        return adapter.listarPropriedades();
    }

    public void deletarPropriedade(Long id) {
        if (!adapter.existePorId(id)) {
            throw new ResourceNotFoundException("Propriedade não encontrada");
        }

        if (adapter.temPessoasVinculadas(id)) {
            throw new BusinessException("Não é possível deletar propriedade com pessoas vinculadas");
        }

        adapter.deletarPropriedade(id);
    }
}
