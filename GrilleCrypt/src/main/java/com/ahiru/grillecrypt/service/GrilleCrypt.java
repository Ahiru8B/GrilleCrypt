package com.ahiru.grillecrypt.service;

import java.util.List;
import java.util.Random;

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
	
	public void deleteElementInMask(int row, int column) {
		this.mask.delete(row, column);
	}
	
	public List<Index> getActiveIndexes() {
		return this.mask.getActiveIndexes();
	}
	
	public List<Index> getFreeIndexes() {
		return this.mask.getFreeIndexes();
	}
	
	public boolean isFree(int row, int column) {
		return this.mask.isFree(row, column);
	}
	
	public String decrypt(String cryptMessage) {
		StringBuilder decrypted = new StringBuilder();
		StringBuilder stringMessage = new StringBuilder(cryptMessage);
		int countElementsInBlock = this.mask.getSize() * this.mask.getSize();
		int countOfBlocks = stringMessage.length() / countElementsInBlock;
		
		for(int i = 0; i < countOfBlocks; i++) {
			decrypted.append(
					this.mask.decryptBlock(
							stringMessage.substring(i * countElementsInBlock, (i + 1) * countElementsInBlock)));
		}
		
		return decrypted.toString();
	}
	
	public String encrypt(String message) {
		StringBuilder encrypted = new StringBuilder();
		StringBuilder stringMessage = new StringBuilder(message);
		Random random = new Random();
		int countElementsInBlock = this.mask.getSize() * this.mask.getSize();
		while(stringMessage.length() % countElementsInBlock > 0) {
			stringMessage.append((char) ('a' + random.nextInt(26)));
		}
		int countOfBlocks = stringMessage.length() / countElementsInBlock;
		
		for(int i = 0; i < countOfBlocks; i++) {
			System.err.println("substring = " + stringMessage.substring(i * countElementsInBlock, (i + 1) * countElementsInBlock));
			encrypted.append(
					this.mask.encryptBlock(
							stringMessage.substring(i * countElementsInBlock, (i + 1) * countElementsInBlock)));
		}
		
		return encrypted.toString();
	}
}
