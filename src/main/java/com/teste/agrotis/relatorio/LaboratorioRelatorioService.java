package com.teste.agrotis.relatorio;

import com.teste.agrotis.laboratorio.LaboratorioRepository;
import com.teste.agrotis.pessoa.PessoaRepository;
import com.teste.agrotis.pessoa.model.Pessoa;
import com.teste.agrotis.relatorio.dto.response.LaboratorioRelatorioResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class LaboratorioRelatorioService {

    private final LaboratorioRepository laboratorioRepository;
    private final PessoaRepository pessoaRepository;

    public LaboratorioRelatorioService(LaboratorioRepository laboratorioRepository, PessoaRepository pessoaRepository) {
        this.laboratorioRepository = laboratorioRepository;
        this.pessoaRepository = pessoaRepository;
    }


    @Transactional(readOnly = true)
    public List<LaboratorioRelatorioResponse> gerarRelatorio(
            LocalDateTime dataInicialStart,
            LocalDateTime dataInicialEnd,
            LocalDateTime dataFinalStart,
            LocalDateTime dataFinalEnd,
            String observacoes,
            Long quantidadeMinima) {

        // Buscar pessoas com os filtros aplicados
        List<Pessoa> pessoasFiltradas = pessoaRepository.findPessoasComFiltros(
                dataInicialStart, dataInicialEnd,
                dataFinalStart, dataFinalEnd,
                observacoes
        );

        // Agrupar pessoas por laboratório
        Map<Long, List<Pessoa>> pessoasPorLaboratorio = pessoasFiltradas.stream()
                .collect(Collectors.groupingBy(p -> p.getLaboratorio().getId()));

        // Criar lista de relatórios
        List<LaboratorioRelatorioResponse> relatorios = laboratorioRepository.findAll().stream()
                .map(lab -> {
                    Long quantidade = pessoasPorLaboratorio.containsKey(lab.getId())
                            ? (long) pessoasPorLaboratorio.get(lab.getId()).size()
                            : 0L;

                    return new LaboratorioRelatorioResponse(
                            lab.getId(),
                            lab.getNome(),
                            quantidade
                    );
                })
                // Filtrar apenas laboratórios com quantidade >= quantidadeMinima
                .filter(dto -> dto.quantidadePessoas() >= quantidadeMinima)
                .collect(Collectors.toList());

        // Ordenar conforme requisitos
        relatorios.sort((a, b) -> {
            // Primeiro ordenar por quantidade (decrescente)
            int compareQuantidade = b.quantidadePessoas().compareTo(a.quantidadePessoas());
            if (compareQuantidade != 0) {
                return compareQuantidade;
            }

            // Se quantidade for igual e houver filtro de data inicial, ordenar por data mais antiga
            if (dataInicialStart != null || dataInicialEnd != null) {
                // Buscar a menor data inicial entre as pessoas de cada laboratório
                LocalDateTime menorDataA = getMenorDataInicial(pessoasPorLaboratorio.get(a.codigo()));
                LocalDateTime menorDataB = getMenorDataInicial(pessoasPorLaboratorio.get(b.codigo()));

                if (menorDataA != null && menorDataB != null) {
                    return menorDataA.compareTo(menorDataB);
                }
            }

            return 0;
        });

        return relatorios;
    }

    private LocalDateTime getMenorDataInicial(List<Pessoa> pessoas) {
        if (pessoas == null || pessoas.isEmpty()) {
            return null;
        }

        return pessoas.stream()
                .map(Pessoa::getDataInicial)
                .min(LocalDateTime::compareTo)
                .orElse(null);
    }
}