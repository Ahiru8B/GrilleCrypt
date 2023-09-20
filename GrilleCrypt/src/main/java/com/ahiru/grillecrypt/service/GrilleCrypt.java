package com.ahiru.grillecrypt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ahiru.grillecrypt.model.Index;
import com.ahiru.grillecrypt.model.Mask;

@Service
public class GrilleCrypt {
	private Mask mask;
	
	public void setSize(int size) {
		this.mask = new Mask(size);
	}
	
	public void addElementInMask(int row, int column) {
		this.mask.add(row, column);
	}
	
	public List<Index> getFreeIndexes() {
		return this.mask.getFreeIndexes();
	}
	
	public boolean isFree(int row, int column) {
		return this.mask.isFree(row, column);
	}
	
	public String decrypt(String cryptMessage) {
		return null;
	}
	
	public String encrypt(String message) {
		return null;
	}
}
