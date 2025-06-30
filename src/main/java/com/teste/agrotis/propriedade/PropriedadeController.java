package com.teste.agrotis.propriedade;

import com.teste.agrotis.propriedade.dto.request.PropriedadeCadastroRequest;
import com.teste.agrotis.propriedade.dto.response.PropriedadeCadastroResponse;
import com.teste.agrotis.propriedade.dto.response.PropriedadeListarResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/propriedades")
public class PropriedadeController {

    private final PropriedadeService service;

    public PropriedadeController(PropriedadeService service) {
        this.service = service;
    }

    @PostMapping("/v1/cadastrar")
    @Operation(summary = "Cadastra uma propriedade")
    public ResponseEntity<PropriedadeCadastroResponse> cadastrarPropriedade(@Valid @RequestBody PropriedadeCadastroRequest request) {
        PropriedadeCadastroResponse response = service.cadastrarPropriedade(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/v1/listar-propriedades")
    @Operation(summary = "Lista todas as propriedades")
    public ResponseEntity<List<PropriedadeListarResponse>> listarPropriedades() {
        List<PropriedadeListarResponse> response = service.listarPropriedades();
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/v1/deletar/{id}")
    @Operation(summary = "Deleta uma propriedade por ID")
    public ResponseEntity<Void> deletarPropriedade(@PathVariable Long id) {
        service.deletarPropriedade(id);
        return ResponseEntity.noContent().build();
    }
}
