package com.joshuaphilips.readlogadministrator.web;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.firebase.auth.FirebaseAuthException;
import com.joshuaphilips.readlogadministrator.models.Book;
import com.joshuaphilips.readlogadministrator.models.UserObject;
import com.joshuaphilips.readlogadministrator.services.BooksService;
import com.joshuaphilips.readlogadministrator.services.UserService;

@Controller
public class BooksController {

	@Autowired
	private BooksService booksService;

	@Autowired
	private UserService userService;

	@GetMapping(value = "/books/{uid}")
	public String getBooks(@PathVariable String uid, Model model)
			throws InterruptedException, ExecutionException, FirebaseAuthException {
		UserObject user = userService.getUser(uid);
		model.addAttribute("user", user);
		model.addAttribute("title", "Books");
		List<Book> books = booksService.getUsersBooks(uid);
		model.addAttribute("books", books);

		return "books";
	}

	@GetMapping(value = "/readingList/{uid}")
	public String getReadingList(@PathVariable String uid, Model model)
			throws InterruptedException, ExecutionException, FirebaseAuthException {
		UserObject user = userService.getUser(uid);
		model.addAttribute("user", user);
		model.addAttribute("title", "Reading List");
		List<Book> books = booksService.getReadingList(uid);
		model.addAttribute("books", books);

		return "books";
	}

	@GetMapping(value = "/books/{uid}/book")
	public String getBookFromBooks(@PathVariable String uid, @RequestParam(required = false) String bookId, Model model)
			throws InterruptedException, ExecutionException {
		Book book = booksService.getBookFromMyBooks(uid, bookId);
		model.addAttribute("book", book);
		return "book";
	}

	@GetMapping(value = "/books/{uid}/readinglist")
	public String getBookReadingList(@PathVariable String uid, @RequestParam(required = false) String bookId,
			Model model) throws InterruptedException, ExecutionException {
		Book book = booksService.getBookFromReading(uid, bookId);
		model.addAttribute("book", book);
		return "book";
	}

}
