package com.ahiru.grillecrypt.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

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
	void addLetterTest() {
		Map<Integer, Map<Integer, Character>> block = blockOfText.getBlockOfText();
		assertEquals('a', block.get(0).get(0));
		assertEquals('b', block.get(0).get(1));
		assertEquals('c', block.get(1).get(0));
		assertEquals('d', block.get(1).get(1));
	}
	
	@Test
	void getLetterTest() {
		assertEquals('a', this.blockOfText.getLetter(0, 0));
		assertEquals('b', this.blockOfText.getLetter(0, 1));
		assertEquals('c', this.blockOfText.getLetter(1, 0));
		assertEquals('d', this.blockOfText.getLetter(1, 1));
	}
}
