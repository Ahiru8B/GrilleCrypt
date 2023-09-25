package com.ahiru.grillecrypt.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahiru.grillecrypt.model.Index;
import com.ahiru.grillecrypt.service.GrilleCrypt;

@RestController
@RequestMapping("/api")
public class EncryptRestController {

	@Autowired
	private GrilleCrypt grilleCrypt;
	private static final Logger log = LoggerFactory.getLogger(EncryptController.class);
	
	@GetMapping("/setSize/{size}")
	public void setSize(@PathVariable Integer size) {
		log.info("Запросили установку размера блока. Размер блока = {}", size);
		this.grilleCrypt.setSize(size);
	}
	
	@GetMapping("/getFreeIndex")
	public List<Index> getFreeIndex() {
		log.info("Запросили список индексов, которые можно заполнить. free index = {}", this.grilleCrypt.getFreeIndexes());
		return this.grilleCrypt.getFreeIndexes();
	}
	
	@GetMapping("/getActive")
	public List<Index> getActive() {
		log.info("Запросили список индексов открытых ячеек в маске. Список ячеек = {}", this.grilleCrypt.getActiveIndexes());
		return this.grilleCrypt.getActiveIndexes();
	}
	
	@GetMapping("/encrypt/{text}")
	public String encrypt(@PathVariable String text) {
		log.info("Запросили зашифровку текста!");
		String encryptedText = this.grilleCrypt.encrypt(text);
		log.info("Зашифрованный текст = {}", encryptedText);
		return encryptedText;
	}
	
	@GetMapping("/decrypt/{text}")
	public String decrypt(@PathVariable String text) {
		log.info("Запросили расшифровку текста!");
		String decryptedText = this.grilleCrypt.decrypt(text);
		log.info("Расшифрованный текст = {}", decryptedText);
		return decryptedText;
	}
	
	@PostMapping("/addIndex")
	public void addIndex(@RequestBody Index index) {
		log.info("Попытались добавить элемент в маску с координатами: Строка = {}, Столбец = {}", index.getRow(), index.getColumn());
		this.grilleCrypt.addElementInMask(index.getRow(), index.getColumn());
	}
	
	@DeleteMapping("/deleteIndex")
	public void deleteIndex(@RequestBody Index index) {
		log.info("Попытались удалить элемент из маски с координатами: Строка = {}, Столбец = {}", index.getRow(), index.getColumn());
		this.grilleCrypt.deleteElementInMask(index.getRow(), index.getColumn());
	}
}
