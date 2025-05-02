package criptografia.spring.entities;

import criptografia.spring.services.CryptoService;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "tb_transaction")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String encryptUserDocument;
	private String encryptCreditCardToken;
	
	private Long valueTransaction;
	
	@Transient
	private String rawUserDocument;
	
	@Transient
	private String rawCreditCardToken;

	public Transaction() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEncryptUserDocument() {
		return encryptUserDocument;
	}

	public void setEncryptUserDocument(String encryptUserDocument) {
		this.encryptUserDocument = encryptUserDocument;
	}

	public Long getValueTransaction() {
		return valueTransaction;
	}

	public void setValueTransaction(Long valueTransaction) {
		this.valueTransaction = valueTransaction;
	}

	public String getRawUserDocument() {
		return rawUserDocument;
	}

	public void setRawUserDocument(String rawUserDocument) {
		this.rawUserDocument = rawUserDocument;
	}

	public String getRawCreditCardToken() {
		return rawCreditCardToken;
	}

	public void setRawCreditCardToken(String rawCreditCardToken) {
		this.rawCreditCardToken = rawCreditCardToken;
	}
	
	public String getEncryptCreditCardToken() {
		return encryptCreditCardToken;
	}

	public void setEncryptCreditCardToken(String encryptCreditCardToken) {
		this.encryptCreditCardToken = encryptCreditCardToken;
	}

	@PrePersist
	public void prePerstist() {
		this.encryptUserDocument = CryptoService.enctrypt(rawUserDocument);
		this.encryptCreditCardToken = CryptoService.enctrypt(rawCreditCardToken); 
	}
	
	@PostLoad
	public void postLoad() {
		this.rawUserDocument = CryptoService.decrypt(encryptUserDocument);
		this.rawCreditCardToken = CryptoService.decrypt(encryptCreditCardToken);
	}
	
	
	
}
