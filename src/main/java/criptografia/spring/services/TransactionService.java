package criptografia.spring.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import criptografia.spring.DesafioCriptografiaApplication;
import criptografia.spring.dto.TransactionRecord;
import criptografia.spring.entities.Transaction;
import criptografia.spring.repository.TransactionRepository;

@Service
public class TransactionService {

    private final DesafioCriptografiaApplication desafioCriptografiaApplication;

	@Autowired
	public TransactionRepository repository;

    TransactionService(DesafioCriptografiaApplication desafioCriptografiaApplication) {
        this.desafioCriptografiaApplication = desafioCriptografiaApplication;
    }
	/*
	 * 
	 * GET
	 * 
	 * */
	 public List<TransactionRecord> listAll() {
	        return repository.findAll().stream()
	            .map(TransactionRecord::toTransactionEntity)   
	            .collect(Collectors.toList());
	    }
	
	public TransactionRecord findById(Long id) {
		
		var transaction = repository.findById(id).get();
		return TransactionRecord.toTransactionEntity(transaction);
	}
	
	/*
	 * 
	 * POST
	 * 
	 * */
	public Transaction create(Transaction transaction) {
		
		transaction.setRawCreditCardToken(transaction.getEncryptCreditCardToken());
		transaction.setRawUserDocument(transaction.getEncryptUserDocument());
		return repository.save(transaction);
	}
	/*
	 * 
	 * PUT
	 * 
	 * */
	public Transaction update(Transaction transaction, Long id) {
		transaction.setId(id);
		return repository.saveAndFlush(transaction);
	}
	/*
	 * 
	 * DELETE
	 * 
	 * */
	public String delete(Long id) {
		repository.deleteById(id);
		return "Transaction deletada com sucesse!";
	}
	
}
