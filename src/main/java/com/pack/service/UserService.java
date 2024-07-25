package com.pack.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pack.exception.UserIdAlreadyExistsException;
import com.pack.exception.UserNotFoundException;
import com.pack.model.User;
import com.pack.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User createUser(User user) throws UserIdAlreadyExistsException {
		if (userRepository.existsById(user.getUserId())) {
			throw new UserIdAlreadyExistsException("User with ID " + user.getUserId() + " already exists.");
		}

		return userRepository.save(user);
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public List<User> getUserByUserId(int userId) throws UserNotFoundException {
		User user = userRepository.findById(userId).orElse(null);
		List<User> list = new ArrayList<User>();
		if (user != null) {

			list = userRepository.findByMasterId(userId);
			list.add(user);
			return list;
		}

		throw new UserNotFoundException("User not found");
	}

	public User getUserByEmail(String emailId) throws UserNotFoundException {
		User user = userRepository.findByEmail(emailId).orElse(null);
		if (user != null) {
			return user;
		} else {
			throw new UserNotFoundException("User not found");
		}
	}

	public User deleteUserByEmail(String emailId) throws UserNotFoundException {
		User user = userRepository.findByEmail(emailId).orElse(null);
		if (user != null) {
			userRepository.delete(user);
			return user;
		} else {
			throw new UserNotFoundException("User not found");
		}

	}

	public User deleteUserByUserId(int userId) throws UserNotFoundException {
		User user = userRepository.findById(userId).orElse(null);
		if (user != null) {
			userRepository.delete(user);
			return user;
		} else {
			throw new UserNotFoundException("User not found");
		}

	}
	public List<User> updateUserByUserId(List<User> updatedUsers) throws UserNotFoundException {

		List<User> updatedList=new ArrayList<User>();
		for (User u : updatedUsers) {
			User user = userRepository.findById(u.getUserId()).orElse(null);
			if (user != null) {
				user.setName(u.getName());
				//user.setEmail(u.getEmail());
				user.setPassword(u.getPassword());
				user.setConfirmPassword(u.getConfirmPassword());

				userRepository.save(user);
				updatedList.add(user);

			} else {
				throw new UserNotFoundException("User not found");
			}
		}
		return updatedList;
	}

	public User loginUser(String email, String password) throws UserNotFoundException {

		User user = userRepository.findByEmailAndPassword(email, password).orElse(null);
		if (user != null) {
			return user;

		} else {
			throw new UserNotFoundException("User not found");
		}

	}

}