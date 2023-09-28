package com.ahiru.grillecrypt.service;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ahiru.grillecrypt.model.Index;
import com.ahiru.grillecrypt.model.Mask;

@Service
public class GrilleCrypt {
	private Mask mask;
	private final static Logger log = LoggerFactory.getLogger(GrilleCrypt.class); 
	
	public void setSize(int size) {
		log.info("Установили размер маски = {}", size);
		this.mask = new Mask(size);
	}
	
	public void addElementInMask(int row, int column) {
		log.info("Попытка добавить элемент в матрицу");
		this.mask.add(row, column);
	}
	
	public void deleteElementInMask(int row, int column) {
		log.info("Попытка удалить элемент из маски с координатам: Строка = {}, Столбец = {}", row, column);
		this.mask.delete(row, column);
	}
	
	public List<Index> getActiveIndexes() {
		log.info("Попытка получить заблокированные индексы");
		return this.mask.getActiveIndexes();
	}
	
	public List<Index> getFreeIndexes() {
		log.info("Попытка получить свободные ячейки");
		return this.mask.getFreeIndexes();
	}
	
	public boolean isFree(int row, int column) {
		log.info("Попытка проверить свободна ли ячейка с координатами: Строка = {}, Столбец = {}", row, column);
		return this.mask.isFree(row, column);
	}
	
	public String decrypt(String cryptMessage) throws ServiceException {
		log.info("Попытка зашифровать сообщение!");
		if(this.mask == null || this.getFreeIndexes().size() > 0) {
			log.error("Попытка расшифровать сообщение с не заполненым ключем");
			throw new ServiceException("Не все элементы ключа установлены!");
		}
		StringBuilder decrypted = new StringBuilder();
		StringBuilder stringMessage = new StringBuilder(cryptMessage);
		int countElementsInBlock = this.mask.getSize() * this.mask.getSize();
		int countOfBlocks = stringMessage.length() / countElementsInBlock;
		
		for(int i = 0; i < countOfBlocks; i++) {
			decrypted.append(
					this.mask.decryptBlock(
							stringMessage.substring(i * countElementsInBlock,
									(i + 1) * countElementsInBlock)));
		}
		return decrypted.toString();
	}
	
	public String encrypt(String message) throws ServiceException {
		log.info("Попытка расшифровать сообщение!");
		if(this.mask == null || this.getFreeIndexes().size() > 0) {
			log.error("Попытка зашифровать сообщение с не заполненым ключем");
			throw new ServiceException("Не все элементы ключа установлены!");
		}
		
		StringBuilder encrypted = new StringBuilder();
		StringBuilder stringMessage = new StringBuilder(message);
		Random random = new Random();
		int countLetterInAlphabet = 33;
		char firstLetterInAlphabet = 'а';
		
		int countElementsInBlock = this.mask.getSize() * this.mask.getSize();
		
		while(stringMessage.length() % countElementsInBlock > 0) {
			stringMessage.append((char) (firstLetterInAlphabet + random.nextInt(countLetterInAlphabet)));
		}
		
		int countOfBlocks = stringMessage.length() / countElementsInBlock;
		
		for(int i = 0; i < countOfBlocks; i++) {
			encrypted.append(
					this.mask.encryptBlock(
							stringMessage.substring(i * countElementsInBlock,
									(i + 1) * countElementsInBlock)));
		}
		
		return encrypted.toString();
	}
}
