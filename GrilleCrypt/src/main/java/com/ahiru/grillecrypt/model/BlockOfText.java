package com.ahiru.grillecrypt.model;

import java.util.HashMap;
import java.util.Map;

public class BlockOfText {
	private Map<Integer, Map<Integer, Character>> blockOfText;

	public BlockOfText() {
		super();
		this.blockOfText = new HashMap<Integer, Map<Integer,Character>>();
	}
	
	public void addLetter(int row, int column, char letter) {
		if(!this.blockOfText.containsKey(row)) {
			this.blockOfText.put(row, new HashMap<Integer, Character>());
		}
		this.blockOfText.get(row).put(column, letter);
	}
	
	public char getLetter(int row, int column) {
		return this.blockOfText.get(row).get(column);
	}
}
