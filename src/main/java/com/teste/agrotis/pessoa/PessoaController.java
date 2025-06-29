package com.teste.agrotis.pessoa;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pessoas")
@Validated
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

//    @PostMapping("/v1/cadastrar")
//    public ResponseEntity<PessoaCadastroResponse> cadastrarPessoa (@Valid @RequestBody PessoaCadastroRequest pessoaCadastroRequest) {
//        return ResponseEntity.ok(pessoaService.cadastrarPessoa(pessoaCadastroRequest));
//    }
}
