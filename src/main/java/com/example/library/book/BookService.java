package com.example.library.book;


import com.example.library.exception.BookNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Samson Effes
 */

@Service
@RequiredArgsConstructor
public class BookService implements IBookService {

	private final BookRepository bookRepository;

	@Override
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public Book add(Book book) {
		return bookRepository.save(book);
	}



	@Override
	public Optional<Book> findById(Long bookId) {
		return Optional.ofNullable(bookRepository.findById(bookId)
				.orElseThrow(() -> new BookNotFoundException("No book found with the id : " + bookId)));
	}



	@Override
	public Optional<Book> findByTitles(String title) {
		return Optional.ofNullable(bookRepository.findByTitle(title)
				.orElseThrow(() -> new BookNotFoundException("No book found with the Title And Edition : " + title)));
	}


	@Override
	public void delete(Long bookId) {
		Optional<Book> theDeletedBook = findById(bookId);
		theDeletedBook.ifPresent(book -> bookRepository.deleteById(book.getId()));
	}



	@Override
	@Transactional
	public void delete1(String title) {
		Optional<Book> theDeletedBook = findByTitles(title);
		theDeletedBook.ifPresent(book -> bookRepository.deleteBookByTitle(book.getTitle()));
	}



	@Override
	public Book update(Book book) {
		return bookRepository.save(book);
	}


}
