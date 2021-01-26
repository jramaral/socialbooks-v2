package com.socialbooks.jra.repository;

import com.socialbooks.jra.domain.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoresRepository extends JpaRepository<Autor, Long> {

    Long  existsAutorsByLivros_Autor_Id (long id);
    boolean existsByLivros_AutorId(long id);
}
