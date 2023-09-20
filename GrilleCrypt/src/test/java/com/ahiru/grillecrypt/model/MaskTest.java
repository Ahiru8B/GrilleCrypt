package com.ahiru.grillecrypt.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
	


}
