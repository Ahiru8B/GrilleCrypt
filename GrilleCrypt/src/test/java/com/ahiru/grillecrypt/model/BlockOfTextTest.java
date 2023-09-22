package com.ahiru.grillecrypt.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BlockOfTextTest {
	private BlockOfText blockOfText;
	
	@BeforeEach
	void init() {
		this.blockOfText = new BlockOfText();
		this.blockOfText.addLetter(0, 0, 'a');
		this.blockOfText.addLetter(0, 1, 'b');
		this.blockOfText.addLetter(1, 0, 'c');
		this.blockOfText.addLetter(1, 1, 'd');
	}
	
	@Test
	void getLetterTest() {
		assertEquals('a', this.blockOfText.getLetter(0, 0));
		assertEquals('b', this.blockOfText.getLetter(0, 1));
		assertEquals('c', this.blockOfText.getLetter(1, 0));
		assertEquals('d', this.blockOfText.getLetter(1, 1));
	}
	
	
}
