package com.ahiru.grillecrypt.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Mask {
	private Set<Index> mask;
	private Integer size;

	public Mask(int size) {
		super();
		this.mask = new HashSet<Index>();
		this.size = size;
	}

	@Deprecated
	public Set<Index> getMask() {
		return mask;
	}

	public Integer getSize() {
		return size;
	}

	public void add(int row, int column) {
		this.mask.add(new Index(row, column));
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

	private List<Index> normalMask() {
		List<Index> mask = new ArrayList<Index>(this.mask);
		return mask;
	}

	private List<Index> maskRotated90degrees(Iterator<Index> mask) {
		List<Index> rotatedMask = new ArrayList<Index>();
		while(mask.hasNext()) {
			Index index = mask.next();
			rotatedMask.add(new Index(index.getColumn(), this.size - 1 - index.getRow()));
		}
		return rotatedMask;
	}
	
	private List<Index> maskRotated180degrees(Iterator<Index> mask) {
		List<Index> rotated90degrees = this.maskRotated90degrees(mask);
		return this.maskRotated90degrees(rotated90degrees.iterator());
	}
	
	private List<Index> maskRotated270degrees(Iterator<Index> mask) {
		List<Index> rotated180degrees = this.maskRotated180degrees(mask);
		return this.maskRotated90degrees(rotated180degrees.iterator());
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
