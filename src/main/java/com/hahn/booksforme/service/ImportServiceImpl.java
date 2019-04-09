package com.hahn.booksforme.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hahn.booksforme.model.Author;
import com.hahn.booksforme.model.Book;
import com.hahn.booksforme.model.Genre;

@Service
public class ImportServiceImpl implements ImportService {

	@Autowired
	AuthorService authorService;

	@Autowired
	GenreService genreService;

	@Autowired
	BookService bookService;

	@Override
	public int importBooks(MultipartFile file) {

		int importAmount = 0;
		
		File convFile;
		try {
			convFile = convert(file);
			try {
				Scanner scanner = new Scanner(convFile);
				Scanner valueScanner = null;
				int index = 0;
				
				while (scanner.hasNextLine()) {
					valueScanner = new Scanner(scanner.nextLine());
					valueScanner.useDelimiter(";");

					Book book = new Book();

					while (valueScanner.hasNext()) {
						String data = valueScanner.next();
						if (index == 0) {
							book.setAsin(data);
						} else if (index == 1) {
							book.setTitle(data);
						} else if (index == 2) {

							Optional<Author> author = authorService.findByName(data);

							if (author.isPresent()) {
								book.setAuthor(author.get());
							} else {
								Author newAuthor = authorService.save(new Author(data));
								book.setAuthor(newAuthor);
							}

						} else if (index == 3) {

							Optional<Genre> genre = genreService.findByName(data);

							if (genre.isPresent()) {
								book.setGenre(genre.get());
							} else {
								Genre newGenre = genreService.save(new Genre(data));
								book.setGenre(newGenre);
							}

						}
						index++;
					}
					index = 0;

					bookService.save(book);
					importAmount++;
				}

				scanner.close();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return importAmount;
	}

	public static File convert(MultipartFile file) throws IOException {
	    File convFile = new File(file.getOriginalFilename());
	    convFile.createNewFile();
	    FileOutputStream fos = new FileOutputStream(convFile);
	    fos.write(file.getBytes());
	    fos.close();
	    return convFile;
	}
	
}
