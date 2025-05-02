package criptografia.spring.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import criptografia.spring.dto.TransactionRecord;
import criptografia.spring.entities.Transaction;
import criptografia.spring.services.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionResource {

	@Autowired
	private TransactionService service;
	/*
	 * 
	 * GET CONTROLLER
	 * 
	 * */
	@GetMapping
	public ResponseEntity<List<TransactionRecord>> listAll(){
		return ResponseEntity.ok(service.listAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TransactionRecord> findById(@PathVariable Long id){
		return ResponseEntity.ok().body(service.findById(id));
	}
	/*
	 * 
	 * POST CONTROLLER
	 * 
	 * */
	@PostMapping
	public ResponseEntity<Transaction> create (@RequestBody Transaction transaction){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(transaction));
	}
	/*
	 * 
	 * PUT CONTROLLER
	 * 
	 * */
	@PutMapping("/{id}")	
	public ResponseEntity<Transaction> update (@RequestBody Transaction transaction, @PathVariable Long id){
		return ResponseEntity.ok(service.update(transaction, id));
	}
	/*
	 * 
	 * DELETE CONTROLLER
	 * 
	 * */
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		return ResponseEntity.ok(service.delete(id));
	}	
}
