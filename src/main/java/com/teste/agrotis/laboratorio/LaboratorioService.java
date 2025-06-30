package com.teste.agrotis.laboratorio;

import com.teste.agrotis.exception.BusinessException;
import com.teste.agrotis.exception.DuplicateResourceException;
import com.teste.agrotis.exception.ResourceNotFoundException;
import com.teste.agrotis.laboratorio.dto.request.LaboratorioCadastroRequest;
import com.teste.agrotis.laboratorio.dto.response.LaboratorioCadastroResponse;
import com.teste.agrotis.laboratorio.dto.response.LaboratorioDropdownResponse;
import com.teste.agrotis.laboratorio.dto.response.LaboratorioListarPorIdResponse;
import com.teste.agrotis.laboratorio.dto.response.LaboratorioListarResponse;
import com.teste.agrotis.laboratorio.model.Laboratorio;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LaboratorioService {

    private final LaboratorioAdapter adapter;

    public LaboratorioService(LaboratorioAdapter adapter) {
        this.adapter = adapter;
    }

    public LaboratorioCadastroResponse cadastrarLaboratorio(LaboratorioCadastroRequest request) {
        if (adapter.existePorNome(request.nome())) {
            throw new DuplicateResourceException("Já existe um laboratório com este nome");
        }

        Laboratorio laboratorio = new Laboratorio();
        laboratorio.setNome(request.nome());
        laboratorio.setAtivo(true);

        return adapter.salvarLaboratorio(laboratorio);
    }

    public LaboratorioListarPorIdResponse buscarPorId(Long id) throws ChangeSetPersister.NotFoundException {
        return adapter.buscarPorId(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    public List<LaboratorioListarResponse> listarLaboratorios() {
        return adapter.listarLaboratorios();
    }

    public List<LaboratorioDropdownResponse> listarAtivos() {
        return adapter.buscarAtivos();
    }

    public void inativar(Long id) {
        if (adapter.temPessoasVinculadas(id)) {
            throw new BusinessException("Não é possível inativar laboratório com pessoas vinculadas");
        }

        Laboratorio laboratorio = adapter.buscarEntidadePorId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Laboratório não encontrado"));

        laboratorio.setAtivo(false);
        adapter.salvarLaboratorio(laboratorio);
    }

    public void deletarLaboratorio(Long id) {
        if (!adapter.existePorId(id)) {
            throw new ResourceNotFoundException("Laboratório não encontrado");
        }

        if (adapter.temPessoasVinculadas(id)) {
            throw new BusinessException("Não é possível deletar laboratório com pessoas vinculadas");
        }

        adapter.deletarLaboratorio(id);
    }

}
