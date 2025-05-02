package criptografia.spring.dto;


import criptografia.spring.entities.Transaction;

public record TransactionRecord(Long id, String userDocument, String creditCardToken, Long valueTransaction) {

	public static  TransactionRecord toTransactionEntity(Transaction transaction) {
		return new TransactionRecord(transaction.getId(), transaction.getRawUserDocument(), transaction.getRawCreditCardToken(), transaction.getValueTransaction());
	}
	
}
