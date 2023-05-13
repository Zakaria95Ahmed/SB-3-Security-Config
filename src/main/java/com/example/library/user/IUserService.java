package com.example.library.user;

import com.example.library.book.Book;

import java.util.List;
import java.util.Optional;

public interface IUserService {

	User add(User user);

	List<UserRecord> getAllUsers();

	User getUser(String email);

	User getUserById(Long id);

	Optional<User> findById(Long userId);

	User update(User user);

	void delete(Long userId);

	void delete(String email);



}
