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
	
}
