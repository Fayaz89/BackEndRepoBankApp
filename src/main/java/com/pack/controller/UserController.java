package com.pack.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pack.config.JWTTokenGeneratorImpl;
import com.pack.config.JWTTokenParser;
import com.pack.exception.CardNotFoundException;
import com.pack.exception.UserIdAlreadyExistsException;
import com.pack.exception.UserNotFoundException;
import com.pack.model.Card;
import com.pack.model.Login;
import com.pack.model.User;
import com.pack.response.ResponseHandler;
import com.pack.service.CardService;
import com.pack.service.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {

	@Value("${jwt.secret}")
	private String secret;

	@Autowired
	private JWTTokenParser jwtTokenParser;

	@Autowired
	private UserService userService;

	@Autowired
	private CardService cardService;

	@Autowired
	private JWTTokenGeneratorImpl jwtTokenGenerator;

	@PostMapping("/register")
	public ResponseEntity<Object> createUser(@RequestBody User user) throws UserIdAlreadyExistsException {

		return ResponseHandler.generateResponse("User created successfully", HttpStatus.CREATED,
				userService.createUser(user));
	}

	@GetMapping("/viewAll")
	public ResponseEntity<Object> getAllUsers() {

		List<User> list = userService.getAllUsers();
		return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK, list);

	}
	
	@GetMapping("/getByUserId/{userId}")
	public ResponseEntity<Object> getUserById(@PathVariable int userId) throws UserNotFoundException {

		return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK,
				userService.getUserByUserId(userId));

	}
	
	
//	@GetMapping("/getByUserId/{userId}")
//	public ResponseEntity<Object> getUserById(@PathVariable int userId) throws UserNotFoundException {
//
////		return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK,
////				userService.getUserByUserId(userId));
//		//int userId = userService.getUserByEmail(emailId).getUserId();
//		List<User> userList = userService.getUserByUserId(userId);
//		List<Card> cardList = cardService.getCardByUserId(userId);
//		Map<String, Object> map = new HashMap<>();
//
//		if (cardList.isEmpty())
//			map.put("userList", userList);
//		else if (userList.isEmpty())
//			map.put("cardList", cardList);
//		else {
//			map.put("userList", userList);
//			map.put("cardList", cardList);
//		}
//
//		return new ResponseEntity<>(map, HttpStatus.CREATED);
//
//	}

	@GetMapping("/{emailId}")
	public ResponseEntity<Object> getUserByEmailId(@PathVariable String emailId) throws UserNotFoundException {

		return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK,
				userService.getUserByEmail(emailId));

	}

	@PutMapping("/updateUser")
	public ResponseEntity<Object> updateUsers(@RequestBody List<User> users) throws UserNotFoundException {

		return ResponseHandler.generateResponse("User updated successfully", HttpStatus.OK,
				userService.updateUserByUserId(users));
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<Object> deleteUserByUserId(@PathVariable int userId) throws UserNotFoundException {
		return ResponseHandler.generateResponse("User deleted successfully", HttpStatus.OK,
				userService.deleteUserByUserId(userId));
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody Login login) throws UserNotFoundException, CardNotFoundException {

//		return ResponseHandler.generateResponse("Successfully retrieved data!", HttpStatus.OK,
//				userService.loginUser(user.getEmail(), user.getPassword()));

		User user = userService.loginUser(login.getEmail(), login.getPassword());

		String token = jwtTokenGenerator.generateToken(user).get("token");

		Map<String, Object> claims = jwtTokenParser.parseToken(token);
//
		String email = (String) claims.get("email");

		int userId = userService.getUserByEmail(email).getUserId();
		List<User> userList = userService.getUserByUserId(userId);
		List<Card> cardList = cardService.getCardByUserId(userId);
		Map<String, Object> map = new HashMap<>();

		map.put("token", token);
		map.put("userId", userId);
//		if (cardList.isEmpty())
//			map.put("userList", userList);
//		else if (userList.isEmpty())
//			map.put("cardList", cardList);
//		else {
//			map.put("userList", userList);
//			map.put("cardList", cardList);
//		}

		return new ResponseEntity<>(map, HttpStatus.CREATED);
	}

	@PostMapping("/addFamilyMember/{userId}")
	public ResponseEntity<Object> addFamilyMember(@PathVariable int userId, @RequestBody User user) throws UserIdAlreadyExistsException
	 {		
		user.setMasterId(userId);
		return ResponseHandler.generateResponse("Family member added successfully", HttpStatus.CREATED,
				userService.createUser(user));
	}

//	@PutMapping("/updateFamilyMember")
//	public ResponseEntity<Object> updateFamilyMember(@PathVariable int userId, @PathVariable int memberId,
//			@RequestBody FamilyMember familyMember) {
//
//		return ResponseHandler.generateResponse("Updated family member details successfully", HttpStatus.OK,
//				familyMemberService.updateFamilyMember(userId, memberId, familyMember));
//	}

//	@DeleteMapping("/{memberId}")
//	public ResponseEntity<Object> deleteFamilyMember(@PathVariable int userId, @PathVariable int memberId)
//			throws FamilyMemberNotFoundException {
//		return ResponseHandler.generateResponse("Deleted family member successfully", HttpStatus.OK,
//				familyMemberService.deleteFamilyMember(memberId));
//	}

}