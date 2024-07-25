//package com.pack.service;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import com.pack.exception.UserIdAlreadyExistsException;
//import com.pack.exception.UserNotFoundException;
//import com.pack.model.User;
//import com.pack.repository.UserRepository;
//
//@ExtendWith(MockitoExtension.class)
//public class UserServiceTest {
//
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private UserService userService;
//
//    private User user;
//
//    @BeforeEach
//    public void setup() {
//        user = new User(1, "John Doe", "john@example.com", "password", "password", 0);
//    }
//
//    @Test
//    public void testCreateUser_Success() throws UserIdAlreadyExistsException {
//        when(userRepository.existsById(user.getUserId())).thenReturn(false);
//        when(userRepository.save(user)).thenReturn(user);
//
//        User createdUser = userService.createUser(user);
//
//        assertNotNull(createdUser);
//        assertEquals(user.getUserId(), createdUser.getUserId());
//        verify(userRepository, times(1)).save(user);
//    }
//
//    @Test
//    public void testCreateUser_UserIdAlreadyExists() {
//        when(userRepository.existsById(user.getUserId())).thenReturn(true);
//
//        assertThrows(UserIdAlreadyExistsException.class, () -> {
//            userService.createUser(user);
//        });
//
//        verify(userRepository, times(0)).save(user);
//    }
//
//    @Test
//    public void testGetAllUsers() {
//        List<User> users = Arrays.asList(user, new User(2, "Jane Doe", "jane@example.com", "password", "password", 0));
//        when(userRepository.findAll()).thenReturn(users);
//
//        List<User> allUsers = userService.getAllUsers();
//
//        assertNotNull(allUsers);
//        assertEquals(2, allUsers.size());
//        verify(userRepository, times(1)).findAll();
//    }
//
//    @Test
//    public void testGetUserByUserId_Success() throws UserNotFoundException {
//        when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
//        when(userRepository.findByMasterId(user.getUserId())).thenReturn(Arrays.asList(new User(2, "Jane Doe", "jane@example.com", "password", "password", 1)));
//
//        List<User> users = new ArrayList<User>(); 
//        		users=userService.getUserByUserId(user.getUserId());
//
//        assertNotNull(users);
//        assertEquals(2, users.size());
//        verify(userRepository, times(1)).findById(user.getUserId());
//        verify(userRepository, times(1)).findByMasterId(user.getUserId());
//    }
//
//    @Test
//    public void testGetUserByUserId_UserNotFound() {
//        when(userRepository.findById(user.getUserId())).thenReturn(Optional.empty());
//
//        assertThrows(UserNotFoundException.class, () -> {
//            userService.getUserByUserId(user.getUserId());
//        });
//
//        verify(userRepository, times(1)).findById(user.getUserId());
//    }
//
//    @Test
//    public void testGetUserByEmail_Success() throws UserNotFoundException {
//        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
//
//        User foundUser = userService.getUserByEmail(user.getEmail());
//
//        assertNotNull(foundUser);
//        assertEquals(user.getEmail(), foundUser.getEmail());
//        verify(userRepository, times(1)).findByEmail(user.getEmail());
//    }
//
//    @Test
//    public void testGetUserByEmail_UserNotFound() {
//        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
//
//        assertThrows(UserNotFoundException.class, () -> {
//            userService.getUserByEmail(user.getEmail());
//        });
//
//        verify(userRepository, times(1)).findByEmail(user.getEmail());
//    }
//
//    @Test
//    public void testDeleteUserByEmail_Success() throws UserNotFoundException {
//        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
//
//        User deletedUser = userService.deleteUserByEmail(user.getEmail());
//
//        assertNotNull(deletedUser);
//        assertEquals(user.getEmail(), deletedUser.getEmail());
//        verify(userRepository, times(1)).delete(user);
//    }
//
//    @Test
//    public void testDeleteUserByEmail_UserNotFound() {
//        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
//
//        assertThrows(UserNotFoundException.class, () -> {
//            userService.deleteUserByEmail(user.getEmail());
//        });
//
//        verify(userRepository, times(0)).delete(user);
//    }
//
//    @Test
//    public void testDeleteUserByUserId_Success() throws UserNotFoundException {
//        when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
//
//        User deletedUser = userService.deleteUserByUserId(user.getUserId());
//
//        assertNotNull(deletedUser);
//        assertEquals(user.getUserId(), deletedUser.getUserId());
//        verify(userRepository, times(1)).delete(user);
//    }
//
//    @Test
//    public void testDeleteUserByUserId_UserNotFound() {
//        when(userRepository.findById(user.getUserId())).thenReturn(Optional.empty());
//
//        assertThrows(UserNotFoundException.class, () -> {
//            userService.deleteUserByUserId(user.getUserId());
//        });
//
//        verify(userRepository, times(0)).delete(user);
//    }
//
//    @Test
//    public void testUpdateUserByUserId_Success() throws UserNotFoundException {
//        List<User> updatedUsers = Arrays.asList(new User(1, "John Smith", "john@example.com", "newpassword", "newpassword", 0));
//        when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
//
//        List<User> result = userService.updateUserByUserId(updatedUsers);
//
//        assertNotNull(result);
//        assertEquals(1, result.size());
//        assertEquals("John Smith", result.get(0).getName());
//        verify(userRepository, times(1)).save(any(User.class));
//    }
//
//    @Test
//    public void testUpdateUserByUserId_UserNotFound() {
//        List<User> updatedUsers = Arrays.asList(new User(1, "John Smith", "john@example.com", "newpassword", "newpassword", 0));
//        when(userRepository.findById(anyInt())).thenReturn(Optional.empty());
//
//        assertThrows(UserNotFoundException.class, () -> {
//            userService.updateUserByUserId(updatedUsers);
//        });
//
//        verify(userRepository, times(0)).save(any(User.class));
//    }
//
//    @Test
//    public void testLoginUser_Success() throws UserNotFoundException {
//        when(userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword())).thenReturn(Optional.of(user));
//
//        User loggedInUser = userService.loginUser(user.getEmail(), user.getPassword());
//
//        assertNotNull(loggedInUser);
//        assertEquals(user.getEmail(), loggedInUser.getEmail());
//        verify(userRepository, times(1)).findByEmailAndPassword(user.getEmail(), user.getPassword());
//    }
//
//    @Test
//    public void testLoginUser_UserNotFound() {
//        when(userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword())).thenReturn(Optional.empty());
//
//        assertThrows(UserNotFoundException.class, () -> {
//            userService.loginUser(user.getEmail(), user.getPassword());
//        });
//
//        verify(userRepository, times(1)).findByEmailAndPassword(user.getEmail(), user.getPassword());
//    }
//}
