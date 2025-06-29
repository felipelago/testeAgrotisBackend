package com.teste.agrotis.propriedade;

import com.teste.agrotis.propriedade.dto.request.PropriedadeCadastroRequest;
import com.teste.agrotis.propriedade.dto.response.PropriedadeCadastroResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/propriedades")
public class PropriedadeController {

    private final PropriedadeService service;

    public PropriedadeController(PropriedadeService service) {
        this.service = service;
    }

    @PostMapping("/v1/cadastrar")
    public ResponseEntity<PropriedadeCadastroResponse> cadastrarPropriedade(@Valid @RequestBody PropriedadeCadastroRequest request) {
        PropriedadeCadastroResponse response = service.cadastrarPropriedade(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

//    @GetMapping("/v1/listar-propriedades")
//    public ResponseEntity<List<PropriedadeListarResponse>> listarPropriedades() {
//        PropriedadeListarResponse response = service.listarPropriedades();
//        return ResponseEntity.ok().body(List.of(response));
//    }
}
