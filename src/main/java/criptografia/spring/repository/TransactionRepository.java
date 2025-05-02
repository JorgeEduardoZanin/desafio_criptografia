package criptografia.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import criptografia.spring.entities.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{}
