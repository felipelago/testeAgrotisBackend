package com.teste.agrotis.laboratorio;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teste.agrotis.laboratorio.dto.response.LaboratorioCadastroResponse;
import com.teste.agrotis.laboratorio.dto.response.LaboratorioDropdownResponse;
import com.teste.agrotis.laboratorio.dto.response.LaboratorioListarPorIdResponse;
import com.teste.agrotis.laboratorio.dto.response.LaboratorioListarResponse;
import com.teste.agrotis.laboratorio.model.Laboratorio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LaboratorioAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LaboratorioAdapter.class);

    private final LaboratorioRepository laboratorioRepository;
    private final ObjectMapper objectMapper;

    public LaboratorioAdapter(LaboratorioRepository laboratorioRepository, ObjectMapper objectMapper) {
        this.laboratorioRepository = laboratorioRepository;
        this.objectMapper = objectMapper;
    }

    public LaboratorioCadastroResponse salvarLaboratorio(Laboratorio laboratorio) {
        Laboratorio saved = laboratorioRepository.save(laboratorio);
        LOGGER.info("Laboratorio cadastrado - Response Body: {}", saved);
        return objectMapper.convertValue(saved, LaboratorioCadastroResponse.class);
    }

    public Optional<LaboratorioListarPorIdResponse> buscarPorId(Long id) {
        return laboratorioRepository.findById(id)
                .map(lab -> objectMapper.convertValue(lab, LaboratorioListarPorIdResponse.class));
    }

    public List<LaboratorioDropdownResponse> buscarAtivos() {
        return laboratorioRepository.findByAtivoTrue().stream()
                .map(lab -> objectMapper.convertValue(lab, LaboratorioDropdownResponse.class))
                .toList();
    }

    public List<LaboratorioListarResponse> listarLaboratorios() {
        return laboratorioRepository.findAll().stream()
                .map(lab -> objectMapper.convertValue(lab, LaboratorioListarResponse.class))
                .toList();
    }

    public boolean existePorNome(String nome) {
        return laboratorioRepository.existsByNome(nome);
    }

    public boolean temPessoasVinculadas(Long laboratorioId) {
        return laboratorioRepository.countPessoasById(laboratorioId) > 0;
    }

//    public void inativar(Long id) {
//        Laboratorio laboratorio = laboratorioRepository.findById(id)
//                .orElseThrow(() -> new NotFoundException("Laboratório não encontrado"));
//
//        laboratorio.setAtivo(false);
//        laboratorioRepository.save(laboratorio);
//    }
}
