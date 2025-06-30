package com.teste.agrotis.pessoa;

import com.teste.agrotis.pessoa.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query("SELECT p FROM Pessoa p " +
            "JOIN FETCH p.propriedade prop " +
            "JOIN FETCH p.laboratorio lab")
    List<Pessoa> findAllWithRelationships();
}
