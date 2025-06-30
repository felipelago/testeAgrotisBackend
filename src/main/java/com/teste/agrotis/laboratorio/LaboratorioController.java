package com.teste.agrotis.laboratorio;

import com.teste.agrotis.laboratorio.dto.request.LaboratorioCadastroRequest;
import com.teste.agrotis.laboratorio.dto.response.LaboratorioCadastroResponse;
import com.teste.agrotis.laboratorio.dto.response.LaboratorioDropdownResponse;
import com.teste.agrotis.laboratorio.dto.response.LaboratorioListarPorIdResponse;
import com.teste.agrotis.laboratorio.dto.response.LaboratorioListarResponse;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Cadastra um novo laboratório")
    public ResponseEntity<LaboratorioCadastroResponse> cadastrarLaboratorio(@Valid @RequestBody LaboratorioCadastroRequest request) {
        LaboratorioCadastroResponse response = laboratorioService.cadastrarLaboratorio(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/v1/listar/{id}")
    @Operation(summary = "Lista um laboratorio por ID (path param)")
    public ResponseEntity<LaboratorioListarPorIdResponse> listarLaboratorioPorId(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(laboratorioService.buscarPorId(id));
    }

    @GetMapping("/v1/listar-laboratorios")
    @Operation(summary = "Lista todos os laboratorios ativos e inativos")
    public ResponseEntity<List<LaboratorioListarResponse>> listarLaboratorios() {
        return ResponseEntity.ok(laboratorioService.listarLaboratorios());
    }

    @GetMapping("/v1/listar-laboratorios-ativos")
    @Operation(summary = "Lista laboratorios ativos para ListViews")
    public List<LaboratorioDropdownResponse> listarAtivos() {
        return laboratorioService.listarAtivos();
    }

    @PatchMapping("/v1/inativar-laboratorio/{id}")
    @Operation(summary = "Inativa um laboratório por ID (path param)")
    public void inativarLaboratorio(@PathVariable Long id) {
        laboratorioService.inativar(id);
    }

    @DeleteMapping("/v1/deletar/{id}")
    @Operation(summary = "Deleta um laboratório por ID")
    public ResponseEntity<Void> deletarLaboratorio(@PathVariable Long id) {
        laboratorioService.deletarLaboratorio(id);
        return ResponseEntity.noContent().build();
    }
}
