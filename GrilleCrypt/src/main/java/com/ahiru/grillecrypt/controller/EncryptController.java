package com.ahiru.grillecrypt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/encrypt")
public class EncryptController {
	private final static Logger log = LoggerFactory.getLogger(EncryptController.class);
	
	@GetMapping
	public String encryptPage() {
		log.info("Запросили страницу encrypt");
		return "encrypt";
	}
}
