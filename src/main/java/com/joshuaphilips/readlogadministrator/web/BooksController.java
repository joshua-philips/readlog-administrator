package com.joshuaphilips.readlogadministrator.web;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.joshuaphilips.readlogadministrator.models.Book;
import com.joshuaphilips.readlogadministrator.services.BooksService;

@RestController
public class BooksController {

	@Autowired
	private BooksService booksService;

	@GetMapping(value = "/books/{uid}")
	public List<Book> getBooks(@PathVariable String uid) throws InterruptedException, ExecutionException {
		return booksService.getUsersBooks(uid);
	}

	@GetMapping(value = "/readingList/{uid}")
	public List<Book> getRadingList(@PathVariable String uid) throws InterruptedException, ExecutionException {
		return booksService.getReadingList(uid);
	}

}
