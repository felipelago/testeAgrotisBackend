package com.teste.agrotis.laboratorio;

import com.teste.agrotis.laboratorio.model.Laboratorio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface LaboratorioRepository extends JpaRepository<Laboratorio, Long> {

    boolean existsByNome(String nome);

    Collection<Object> findByAtivoTrue();

    int countPessoasById(Long laboratorioId);
}
