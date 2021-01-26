package com.socialbooks.jra.repository;

import com.socialbooks.jra.domain.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivrosRepository extends JpaRepository<Livro, Long> {

}
