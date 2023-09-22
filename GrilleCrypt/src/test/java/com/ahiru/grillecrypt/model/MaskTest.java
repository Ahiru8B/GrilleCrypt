package com.ahiru.grillecrypt.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MaskTest {

	private Mask mask;
	
	@BeforeEach
	void init() {
		this.mask = new Mask(2);
	}

	@Test
	void decryptBlockTest() {
		this.mask.add(0, 0);
		String encryptedBlock = "abcd";
		String decryptedBlock = "abdc";
		assertEquals(decryptedBlock, this.mask.decryptBlock(encryptedBlock));
		
		this.mask = new Mask(4);
		this.mask.add(0, 0);
		this.mask.add(0, 1);
		this.mask.add(1, 0);
		this.mask.add(1, 1);
		encryptedBlock = "ab12cd3478qw90er";
		decryptedBlock = "abcd1234qwer7890";
		assertEquals(decryptedBlock, this.mask.decryptBlock(encryptedBlock));
	}
	
	@Test
	void encryptBlockTest() {
		this.mask.add(0, 0);
		String decryptedBlock = "abdc";
		String encryptedBlock = "abcd";
		assertEquals(encryptedBlock, this.mask.encryptBlock(decryptedBlock));
		
		this.mask = new Mask(4);
		this.mask.add(0, 0);
		this.mask.add(0, 1);
		this.mask.add(1, 0);
		this.mask.add(1, 1);
		
		encryptedBlock = "ab12cd3478qw90er";
		decryptedBlock = "abcd1234qwer7890";
		
		assertEquals(encryptedBlock, this.mask.encryptBlock(decryptedBlock));
	}
	
	@Test
	void isFreeTest() {
		assertTrue(this.mask.isFree(0, 0));
		assertTrue(this.mask.isFree(0, 1));
		assertTrue(this.mask.isFree(1, 0));
		assertTrue(this.mask.isFree(1, 1));
		this.mask.add(0, 0);
		assertFalse(this.mask.isFree(0, 0));
		assertFalse(this.mask.isFree(0, 1));
		assertFalse(this.mask.isFree(1, 0));
		assertFalse(this.mask.isFree(1, 1));
	}
	
	@Test
	void getFreeIndexesTest() {
		assertTrue(this.mask.getFreeIndexes().size() == 4);
		this.mask.add(0, 0);
		assertTrue(this.mask.getFreeIndexes().size() == 0);
	}

}
