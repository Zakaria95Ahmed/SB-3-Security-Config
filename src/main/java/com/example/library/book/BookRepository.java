package com.example.library.book;

import com.example.library.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

	Optional<Book> findByTitle(String title);

	void deleteBookByTitle(String title);



}
