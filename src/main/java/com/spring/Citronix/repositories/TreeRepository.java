package com.spring.Citronix.repositories;

import com.spring.Citronix.entities.Tree;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreeRepository extends JpaRepository<Tree, Long> {
}
