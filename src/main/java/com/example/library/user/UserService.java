package com.example.library.user;


import com.example.library.book.Book;
import com.example.library.exception.BookNotFoundException;
import com.example.library.exception.UserAlreadyExistsException;
import com.example.library.exception.UserNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public User add(User user) {
		Optional<User> theUser = userRepository.findByEmail(user.getEmail());
		if (theUser.isPresent()) {
			throw new UserAlreadyExistsException("A user with " + user.getEmail() + " already exists");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public List<UserRecord> getAllUsers() {
		return userRepository.findAll()
				.stream()
				.map(user -> new UserRecord(
						user.getId(),
						user.getFirstName(),
						user.getLastName(),
						user.getEmail())).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void delete(String email) {
		userRepository.deleteByEmail(email);
	}

	@Override
	public void delete(Long userId) {
		Optional<User> deletedUser = findById(userId);
		deletedUser.ifPresent(user -> userRepository.deleteById(user.getId()));
	}

	@Override
	public Optional<User> findById(Long userId) {
		return Optional.ofNullable(userRepository.findById(userId)
				.orElseThrow(() -> new BookNotFoundException("No User found with the id : " + userId)));
	}

	@Override
	public User getUser(String email) {
		return userRepository.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundException("User not found with that Email :->" + email));
	}

	@Override
	public User getUserById(Long userId) {
		return userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User not found By this Id :->"+userId));
	}

	@Override
	public User update(User user) {
		user.setRoles(user.getRoles());
		return userRepository.save(user);
	}


}
