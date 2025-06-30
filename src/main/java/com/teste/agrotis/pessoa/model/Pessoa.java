package com.teste.agrotis.pessoa.model;

import com.teste.agrotis.laboratorio.model.Laboratorio;
import com.teste.agrotis.propriedade.model.Propriedade;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pessoas")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(name = "data_inicial", nullable = false)
    private LocalDateTime  dataInicial;

    @Column(name = "data_final", nullable = false)
    private LocalDateTime  dataFinal;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "propriedade_id", nullable = false)
    private Propriedade propriedade;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "laboratorio_id", nullable = false)
    private Laboratorio laboratorio;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    //Construtores
    public Pessoa(Long id, String nome, LocalDateTime dataInicial, LocalDateTime dataFinal, Propriedade propriedade, Laboratorio laboratorio, String observacoes) {
        this.id = id;
        this.nome = nome;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.propriedade = propriedade;
        this.laboratorio = laboratorio;
        this.observacoes = observacoes;
    }

    public Pessoa() {
    }

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDateTime dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDateTime getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDateTime dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Propriedade getPropriedade() {
        return propriedade;
    }

    public void setPropriedade(Propriedade propriedade) {
        this.propriedade = propriedade;
    }

    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}