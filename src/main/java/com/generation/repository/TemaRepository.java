package com.generation.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.generation.model.TemaModel;

public interface TemaRepository extends JpaRepository<TemaModel, Long> {

    List<TemaModel> findAllByDescricaoContainingIgnoreCase(String descricao);
}
