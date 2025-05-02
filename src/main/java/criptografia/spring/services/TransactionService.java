package criptografia.spring.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import criptografia.spring.dto.TransactionRecord;
import criptografia.spring.dto.TransactionRecordRequest;
import criptografia.spring.entities.Transaction;
import criptografia.spring.repository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	public TransactionRepository repository;
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
	public TransactionRecord create(Transaction transaction) {
		
		transaction.setRawCreditCardToken(transaction.getEncryptCreditCardToken());
		transaction.setRawUserDocument(transaction.getEncryptUserDocument());
		repository.save(transaction);
		return TransactionRecord.toTransactionEntity(transaction);
	}
	/*
	 * 
	 * PUT
	 * 
	 * */
	public Transaction update(TransactionRecordRequest transaction, Long id) {
		var findTransactionById = repository.findById(id);
		findTransactionById.get().setValueTransaction(transaction.valueTransaction());
		repository.saveAndFlush(findTransactionById.get());
		return findTransactionById.get();
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
