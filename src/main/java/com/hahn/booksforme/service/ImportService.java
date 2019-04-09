package com.hahn.booksforme.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImportService {

	public int importBooks(MultipartFile file);
}
