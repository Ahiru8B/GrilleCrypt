package com.ahiru.grillecrypt.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GrilleCryptTest {
	
	@Autowired
	private GrilleCrypt grileCrypt;

	@BeforeEach
	void init() {
		this.grileCrypt.setSize(2);
	}
	
	@Test
	void encryptTest() {
		String encrypt = "abcd";
		String decrypt = "abdc";
		this.grileCrypt.addElementInMask(0, 0);
		assertEquals(decrypt, this.grileCrypt.encrypt(encrypt));
	}
	
	@Test
	void decryptTest() {
		String encrypt = "abcd";
		String decrypt = "abdc";
		this.grileCrypt.addElementInMask(0, 0);
		assertEquals(encrypt, this.grileCrypt.decrypt(decrypt));	
	}
	
	@Test
	void serviceTest() {
		String message = "Привет Мир!";
		this.grileCrypt.setSize(4);
		this.grileCrypt.addElementInMask(0, 0);
		this.grileCrypt.addElementInMask(1, 1);
		this.grileCrypt.addElementInMask(0, 2);
		this.grileCrypt.addElementInMask(1, 3);
		String encryptedText = this.grileCrypt.encrypt(message);
		assertEquals(4 * 4, encryptedText.length());
		String decryptedText = this.grileCrypt.decrypt(encryptedText);
		assertEquals(message, decryptedText.substring(0, message.length()));
	}

}
