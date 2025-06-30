package com.teste.agrotis.pessoa;

import com.teste.agrotis.pessoa.dto.request.PessoaCadastroRequest;
import com.teste.agrotis.pessoa.dto.response.PessoaCadastroResponse;
import com.teste.agrotis.pessoa.dto.response.PessoaListarResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
@Validated
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping("/v1/cadastrar")
    @Operation(summary = "Cadastra uma nova pessoa")
    public ResponseEntity<PessoaCadastroResponse> cadastrarPessoa(@Valid @RequestBody PessoaCadastroRequest pessoaCadastroRequest) {
        return ResponseEntity.ok(pessoaService.salvarPessoa(pessoaCadastroRequest));
    }

    @GetMapping("/v1/listar-pessoas")
    @Operation(summary = "Listar todas as pessoas")
    public ResponseEntity<List<PessoaListarResponse>> listarPessoas() {
        return ResponseEntity.ok(pessoaService.listarPessoas());
    }

    @DeleteMapping("/v1/deletar/{id}")
    @Operation(summary = "Deleta uma pessoa por ID")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Long id) {
        pessoaService.deletarPessoa(id);
        return ResponseEntity.noContent().build();
    }
}
