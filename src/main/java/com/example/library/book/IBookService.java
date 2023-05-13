package com.example.library.book;

import com.example.library.user.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IBookService {
	
	
    List<Book> getAllBooks();
    Book add(Book book);

    Optional<Book> findById(Long bookId);
    void delete(Long bookId);

    Optional<Book> findByTitles(String title);
    void delete1(String title);

    Book update(Book book);


}
