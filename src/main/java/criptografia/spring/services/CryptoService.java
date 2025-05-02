package criptografia.spring.services;

import org.jasypt.util.text.StrongTextEncryptor;
import org.springframework.stereotype.Service;

@Service
public class CryptoService {

	private static final StrongTextEncryptor encryptor;
	
	static {
		
		//"senha secreta" que esta nas variaveis de ambiente do meu computador
		encryptor = new StrongTextEncryptor();
		encryptor.setPassword(System.getenv("APP_KEY"));
	}
	
	public static String enctrypt(String rawText) {
		return encryptor.encrypt(rawText);
	}
	
	public static String decrypt(String encryptedText) {
		return encryptor.decrypt(encryptedText);
	}
	
	
}
