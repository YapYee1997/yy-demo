package com.example.yy_demo.user.controller;

import com.example.yy_demo.user.model.User;
import com.example.yy_demo.user.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);
	private final ObjectMapper objectMapper = new ObjectMapper();
	private final UserService userService;
	
	@Autowired
    public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/add")
    public ResponseEntity<?> createUser(@RequestBody Object request) {
        logger.info("Received request: {}", request);

        if (request instanceof List) {
            List<User> users = objectMapper.convertValue(request, new TypeReference<List<User>>() {});
    	    logger.info("Response: {}", users);
            return ResponseEntity.ok(userService.createUsers(users));
        } else {
            User user = objectMapper.convertValue(request, User.class);
    	    logger.info("Response: {}", user);
            return ResponseEntity.ok(userService.createUser(user));
        }
    }
	
	@PutMapping("/update/{id}")
	public User updateUser(@PathVariable Long id, @RequestBody User user) {
	    logger.info("Received PUT request to update user ID: {}, Payload: {}", id, user);

	    User updatedUser = userService.updateUser(id, user);
	    
	    logger.info("Response: {}", updatedUser);
	    return updatedUser;
	}
	
	@GetMapping("/view")
    public List<User> getAllUsers() {
	    logger.info("Received GET request for all users");
	    List<User> userList = userService.getAllUsers();
	    logger.info("Response: {}", userList);
		return userList;
	}
	
	@GetMapping("/view/{id}")
	public User getUserById(@PathVariable Long id) {
	    logger.info("Received GET request for user ID: {}", id);
	    User user = userService.getUserById(id);
	    logger.info("Response: {}", user);
	    return user;
	}
	
	@GetMapping("/paged")
    public Page<User> getUsersPaginated(@RequestParam(defaultValue = "0") int page) {
	    logger.info("Received GET request for user page: {}", page);
	    Page<User> userPage = userService.getUserPaginated(page);
	    logger.info("Response: Current Page Users: {}", userPage.getContent());
		return userPage;
	}
	
	@GetMapping("/external")
    public String callExternalApi() {
	    logger.info("Received GET request for calling third party Api");
	    String APIRespone = userService.callExternalApi();
	    logger.info("Response: {}", APIRespone);
        return APIRespone;
    }
	
}
