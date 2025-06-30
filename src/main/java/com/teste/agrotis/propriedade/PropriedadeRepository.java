package com.teste.agrotis.propriedade;

import com.teste.agrotis.propriedade.model.Propriedade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PropriedadeRepository extends JpaRepository<Propriedade, Long> {

    boolean existsByNome(String nome);

    @Query("SELECT COUNT(p) FROM Pessoa p WHERE p.propriedade.id = :propriedadeId")
    int countPessoasById(@Param("propriedadeId") Long propriedadeId);
}
