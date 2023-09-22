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
		log.info("setSize size = {}", size);
		this.grilleCrypt.setSize(size);
	}
	
	@GetMapping("/getFreeIndex")
	public List<Index> getFreeIndex() {
		log.info("free index = {}", this.grilleCrypt.getFreeIndexes());
		return this.grilleCrypt.getFreeIndexes();
	}
	
	@GetMapping("/getActive")
	public List<Index> getActive() {
		log.info("getActive = {}", this.grilleCrypt.getActiveIndexes());
		return this.grilleCrypt.getActiveIndexes();
	}
	
	@GetMapping("/encrypt/{text}")
	public String encrypt(@PathVariable String text) {
		log.info("encrypt text = {}", text);
		String encryptedText = this.grilleCrypt.encrypt(text);
		log.info("encrypted text = {}", encryptedText);
		return encryptedText;
	}
	
	@GetMapping("/decrypt/{text}")
	public String decrypt(@PathVariable String text) {
		log.info("encrypt text = {}", text);
		String decryptedText = this.grilleCrypt.decrypt(text);
		log.info("encrypted text = {}", decryptedText);
		return decryptedText;
	}
	
	@PostMapping("/addIndex")
	public void addIndex(@RequestBody Index index) {
		log.info("add index! row = {}, column = {}", index.getRow(), index.getColumn());
		this.grilleCrypt.addElementInMask(index.getRow(), index.getColumn());
	}
	
	@DeleteMapping("/deleteIndex")
	public void deleteIndex(@RequestBody Index index) {
		log.info("add index! row = {}, column = {}", index.getRow(), index.getColumn());
		this.grilleCrypt.deleteElementInMask(index.getRow(), index.getColumn());
	}
}
