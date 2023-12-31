package com.ahiru.grillecrypt.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class Mask {
	private Set<Index> mask;
	private Integer size;

	public Mask(int size) {
		super();
		this.mask = new TreeSet<Index>();
		this.size = size;
	}

	public Integer getSize() {
		return size;
	}

	public void add(int row, int column) {
		this.mask.add(new Index(row, column));
	}
	
	public List<Index> getActiveIndexes() {
		return new ArrayList<Index>(this.mask);
	}
	
	public void delete(int row, int column) {
		this.mask.remove(new Index(row, column));
	}

	public String encryptBlock(String message) {
		Map<Integer, Map<Integer, Character>> blockOfText = new HashMap<Integer, Map<Integer,Character>>();
		
		for(int i = 0; i < this.size; i++) {
			blockOfText.put(i, new HashMap<Integer, Character>());
		}
		
		this.setTextUnderMask(blockOfText, message.substring(0, message.length() / 4), this.normalMask().iterator());
		this.setTextUnderMask(blockOfText, message.substring(message.length() / 4, message.length() / 2), this.maskRotated90degrees(this.mask.iterator()).iterator());
		this.setTextUnderMask(blockOfText, message.substring(message.length() / 2, (message.length() / 4) * 3), this.maskRotated180degrees(this.mask.iterator()).iterator());
		this.setTextUnderMask(blockOfText, message.substring((message.length() / 4) * 3), this.maskRotated270degrees(this.mask.iterator()).iterator());
		
		StringBuilder encodedMessage = new StringBuilder();
		
		for(int i = 0; i < this.size; i++) {
			
			for(int j = 0; j < this.size; j++) {
				encodedMessage.append(blockOfText.get(i).get(j));
			}
		}
		
		return encodedMessage.toString();
	}
	
	public String decryptBlock(String block) {
		List<List<Character>> message = this.stringToBlock(block);
		StringBuilder decode = new StringBuilder();
		decode.append(this.textUnderMask(message, this.normalMask()));
		decode.append(this.textUnderMask(message, this.maskRotated90degrees(this.mask.iterator())));
		decode.append(this.textUnderMask(message, this.maskRotated180degrees(this.mask.iterator())));
		decode.append(this.textUnderMask(message, this.maskRotated270degrees(this.mask.iterator())));
		return decode.toString();
	}
	
	public boolean isFree(int row, int column) {
		Index testIndex = new Index(row, column);
		if(this.normalMask().contains(testIndex)
				|| this.maskRotated90degrees(this.mask.iterator()).contains(testIndex)
				|| this.maskRotated180degrees(this.mask.iterator()).contains(testIndex)
				|| this.maskRotated270degrees(this.mask.iterator()).contains(testIndex)) {
			return false;
		} else {
			return true;
		}
	}
	
	public List<Index> getFreeIndexes() {
		List<Index> freeIndexes = new ArrayList<Index>();
		for(int i = 0; i < this.size; i++) {
			for(int j = 0; j < this.size; j++) {
				if(this.isFree(i, j)) {
					freeIndexes.add(new Index(i, j));
				}
			}
		}
		return freeIndexes;
	}
	
	public void generateRandomMask() {
		List<Index> freeIndexes = this.getFreeIndexes();
		while(!freeIndexes.isEmpty()) {
			Random random = new Random();
			int in = random.nextInt(freeIndexes.size());
			this.mask.add(freeIndexes.get(in));
			freeIndexes = this.getFreeIndexes();
		}
	}
	
	private List<Index> normalMask() {
		List<Index> mask = new ArrayList<Index>(this.mask);
		Collections.sort(mask);
		return mask;
	}

	private List<Index> maskRotated90degrees(Iterator<Index> mask) {
		List<Index> rotatedMask = new ArrayList<Index>();
		while(mask.hasNext()) {
			Index index = mask.next();
			rotatedMask.add(new Index(index.getColumn(), this.size - 1 - index.getRow()));
		}
		Collections.sort(rotatedMask);
		return rotatedMask;
	}
	
	private List<Index> maskRotated180degrees(Iterator<Index> mask) {
		List<Index> rotated90degrees = this.maskRotated90degrees(mask);
		List<Index> rotatedMask = this.maskRotated90degrees(rotated90degrees.iterator());
		Collections.sort(rotatedMask);
		return rotatedMask;
	}
	
	private List<Index> maskRotated270degrees(Iterator<Index> mask) {
		List<Index> rotated180degrees = this.maskRotated180degrees(mask);
		List<Index> rotatedMask = this.maskRotated90degrees(rotated180degrees.iterator());
		Collections.sort(rotatedMask);
		return rotatedMask;
	}
	
	private void setTextUnderMask(Map<Integer, Map<Integer, Character>> block, String message, Iterator<Index> mask) {
		int i = 0;
		while(mask.hasNext()) {
			Index index = mask.next();
			block.get(index.getRow()).put(index.getColumn(), message.charAt(i));
			i++;
		}
	}
	
	private String textUnderMask(List<List<Character>> blockOfCharacter, List<Index> mask) {
		StringBuilder text = new StringBuilder();
		for (Index index : mask) {
			text.append(blockOfCharacter.get(index.getRow()).get(index.getColumn()));
		}
		return text.toString();
	}
	
	private List<List<Character>> stringToBlock(String string) {
		List<List<Character>> blockOfCharacter = new ArrayList<List<Character>>();
		for (int i = 0; i < this.size; i++) {
			List<Character> row = new ArrayList<Character>();
			blockOfCharacter.add(row);
			for (int j = 0; j < this.size; j++) {
				row.add(string.charAt(i * this.size + j));
			}
		}
		return blockOfCharacter;
	}
}
