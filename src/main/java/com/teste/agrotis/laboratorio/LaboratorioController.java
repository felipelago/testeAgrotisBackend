package com.teste.agrotis.laboratorio;

import com.teste.agrotis.laboratorio.dto.request.LaboratorioCadastroRequest;
import com.teste.agrotis.laboratorio.dto.response.LaboratorioCadastroResponse;
import com.teste.agrotis.laboratorio.dto.response.LaboratorioDropdownResponse;
import com.teste.agrotis.laboratorio.dto.response.LaboratorioListarPorIdResponse;
import com.teste.agrotis.laboratorio.dto.response.LaboratorioListarResponse;
import jakarta.validation.Valid;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/laboratorios")
@Validated
public class LaboratorioController {

    private final LaboratorioService laboratorioService;

    public LaboratorioController(LaboratorioService laboratorioService) {
        this.laboratorioService = laboratorioService;
    }

    @PostMapping("/v1/cadastrar")
    public ResponseEntity<LaboratorioCadastroResponse> cadastrarLaboratorio(@Valid @RequestBody LaboratorioCadastroRequest request) {
        LaboratorioCadastroResponse response = laboratorioService.cadastrarLaboratorio(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/v1/listar/{id}")
    public ResponseEntity<LaboratorioListarPorIdResponse> listarLaboratorioPorId(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(laboratorioService.buscarPorId(id));
    }

    @GetMapping("/v1/listar-laboratorios")
    public ResponseEntity<List<LaboratorioListarResponse>> listarLaboratorios(){
        return ResponseEntity.ok(laboratorioService.listarLaboratorios());
    }

    @GetMapping("/v1/listar-laboratorios-ativos")
    public List<LaboratorioDropdownResponse> listarAtivos() {
        return laboratorioService.listarAtivos();
    }

    @PatchMapping("/v1/inativar-laboratorio/{id}")
    public void inativarLaboratorio(@PathVariable Long id) {
        laboratorioService.inativar(id);
    }
}
