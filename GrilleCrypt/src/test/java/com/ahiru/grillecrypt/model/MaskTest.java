package com.ahiru.grillecrypt.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MaskTest {

	private Mask mask;

	@BeforeEach
	void init() {
		this.mask = new Mask(2);
	}

	@Test
	void addTest() {
		this.mask.add(0, 0);
		assertEquals(2, this.mask.getSize());
		assertTrue(this.mask.getMask().contains(new Index(0, 0)));
	}
	
	@Test
	void decryptBlockTest() {
		this.mask.add(0, 0);
		String ecnryptedBlock = "abcd";
		String decryptedBlock = "abdc";
		assertEquals(decryptedBlock, this.mask.decryptBlock(ecnryptedBlock));
	}
	
	@Test
	void encryptBlockTest() {
		this.mask.add(0, 0);
		String decryptedBlock = "abdc";
		String ecnryptedBlock = "abcd";
		assertEquals(ecnryptedBlock, this.mask.encryptBlock(decryptedBlock));
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
	


}
