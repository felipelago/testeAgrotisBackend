package com.teste.agrotis.propriedade;

import com.teste.agrotis.propriedade.model.Propriedade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropriedadeRepository extends JpaRepository<Propriedade, Long> {
}
