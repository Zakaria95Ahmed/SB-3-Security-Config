package com.example.library.user;

import com.example.library.book.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	//////	http://localhost:8080/users/all
	@GetMapping("/all")
	public ResponseEntity<List<UserRecord>> getAllUsers() {
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.FOUND);
	}


	///	http://localhost:8080/users/add
	@PostMapping("/add")
	public ResponseEntity<User> add(@RequestBody User user) {
		return new ResponseEntity<>(userService.add(user),HttpStatus.CREATED);
	}

	////--- http://localhost:8080/users/find/{email}
	@GetMapping("/find/{email}")
	public User getByEmail(@PathVariable("email") String email) {
		return userService.getUser(email);
	}

	////--- http://localhost:8080/users/find_email?email={email}
	@GetMapping("/find_email")
	public User findByEmail(@RequestParam("email") String email) {
		return userService.getUser(email);
	}


	//////--http://localhost:8080/users/get/{id}
	@GetMapping("/get/{id}")
	public User getUserById(@PathVariable("id") Long id) {
		return userService.getUserById(id);
	}

	////-- http://localhost:8080/users/user?id=3
	@GetMapping("/user")
	public Optional<User> getById(@RequestParam("id") Long userId) {
		return userService.findById(userId);
	}

     /////	http://localhost:8080/users/update
	@PutMapping("/update")
	public ResponseEntity<User> update(@RequestBody User user) {
		return new ResponseEntity<>(userService.update(user),HttpStatus.OK);
	}

	////	http://localhost:8080/users/{email}
	@DeleteMapping("/{email}")
	public void delete(@PathVariable("email") String email) {
		userService.delete(email);
	}

	////	http://localhost:8080/users/delete/{id}
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable("id") Long userId) {
		userService.delete(userId);
	}

	////--http://localhost:8080/users/deleteByMail?email={email}
	@DeleteMapping("/deleteByMail")
	public void deleteP(@RequestParam("email") String email) {
		userService.delete(email);
	}

	////--http://localhost:8080/users/deleteById?id={id}
	@DeleteMapping("/deleteById")
	public void deleteP(@RequestParam("id") Long userId) {
		userService.delete(userId);
	}


}
