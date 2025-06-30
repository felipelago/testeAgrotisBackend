package com.teste.agrotis.laboratorio;

import com.teste.agrotis.exception.DuplicateResourceException;
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

//    public LaboratorioResponse atualizar(Long id, LaboratorioUpdateRequest request) {
//        // Buscar entidade
//        Laboratorio laboratorio = adapter.buscarEntidadePorId(id)
//                .orElseThrow(() -> new NotFoundException("Laboratório não encontrado"));
//
//        // Validar nome duplicado
//        if (!laboratorio.getNome().equals(request.nome()) &&
//                adapter.existePorNomeAndIdNot(request.nome(), id)) {
//            throw new BusinessException("Já existe outro laboratório com este nome");
//        }
//
//        // Atualizar
//        laboratorio.setNome(request.nome());
//
//        return adapter.salvarLaboratorio(laboratorio);
//    }

//    public void inativar(Long id) {
//        if (adapter.temPessoasVinculadas(id)) {
//            throw new BusinessException("Não é possível inativar laboratório com pessoas vinculadas");
//        }
//
//        Laboratorio laboratorio = adapter.buscarEntidadePorId(id)
//                .orElseThrow(() -> new NotFoundException("Laboratório não encontrado"));
//
//        laboratorio.setAtivo(false);
//        adapter.salvarLaboratorio(laboratorio);
//    }

}
