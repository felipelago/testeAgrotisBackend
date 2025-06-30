package com.teste.agrotis.relatorio;

import com.teste.agrotis.relatorio.dto.response.LaboratorioRelatorioResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/laboratorios")
public class LaboratorioRelatorioController {

    private final LaboratorioRelatorioService laboratorioRelatorioService;

    public LaboratorioRelatorioController(LaboratorioRelatorioService laboratorioRelatorioService) {
        this.laboratorioRelatorioService = laboratorioRelatorioService;
    }

    @GetMapping("/v1/relatorio")
    public ResponseEntity<List<LaboratorioRelatorioResponse>> gerarRelatorio(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime dataInicialStart,

            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime dataInicialEnd,

            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime dataFinalStart,

            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime dataFinalEnd,

            @RequestParam(required = false)
            String observacoes,

            @RequestParam @NotNull(message = "Quantidade mínima é obrigatória")
            Long quantidadeMinima) {

        List<LaboratorioRelatorioResponse> relatorio = laboratorioRelatorioService.gerarRelatorio(
                dataInicialStart,
                dataInicialEnd,
                dataFinalStart,
                dataFinalEnd,
                observacoes,
                quantidadeMinima
        );

        return ResponseEntity.ok(relatorio);
    }
}
