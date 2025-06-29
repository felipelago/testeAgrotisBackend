package com.teste.agrotis.propriedade.model;

import com.teste.agrotis.pessoa.model.Pessoa;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "propriedades")
public class Propriedade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false)
    private Boolean ativo = true;

    @OneToMany(mappedBy = "propriedade")
    private List<Pessoa> pessoas = new ArrayList<>();

    //Construtores
    public Propriedade() {
    }

    public Propriedade(Long id, String nome, Boolean ativo, List<Pessoa> pessoas) {
        this.id = id;
        this.nome = nome;
        this.ativo = ativo;
        this.pessoas = pessoas;
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

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }
}
