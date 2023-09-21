package com.ahiru.grillecrypt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
