package com.ahiru.grillecrypt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/encrypt")
public class EncryptController {
	
	@GetMapping
	public String encryptPage() {
		return "encrypt";
	}
}
