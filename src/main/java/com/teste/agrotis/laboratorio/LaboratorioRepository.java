package com.teste.agrotis.laboratorio;

import com.teste.agrotis.laboratorio.model.Laboratorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface LaboratorioRepository extends JpaRepository<Laboratorio, Long> {

    boolean existsByNome(String nome);

    Collection<Object> findByAtivoTrue();

    @Query("SELECT COUNT(p) FROM Pessoa p WHERE p.laboratorio.id = :laboratorioId")
    int countPessoasById(@Param("laboratorioId") Long laboratorioId);
}
