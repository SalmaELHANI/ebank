package com.ebank.repository;

import com.ebank.entity.Operation;
import com.ebank.entity.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {
    List<Operation> findByCompteOrderByDateDesc(Compte compte);
}
