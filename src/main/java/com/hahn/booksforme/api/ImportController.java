package com.hahn.booksforme.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hahn.booksforme.service.ImportService;

@RestController
public class ImportController {

	@Autowired
	ImportService importService;
	
	@PostMapping("/import/books")
	public String importBooks(@RequestBody MultipartFile file) {

		int importAmount = importService.importBooks(file);
		
		return "Imported " + importAmount + " books successfully ";
	}

}
