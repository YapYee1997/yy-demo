package com.example.yy_demo.user.service;

import com.example.yy_demo.user.model.User;
import com.example.yy_demo.user.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
	private final UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Transactional
	public User createUser(User user) {
	    return userRepository.save(user);
	}

	@Transactional
	public List<User> createUsers(List<User> users) {
	    return userRepository.saveAll(users);
	}
	
	@Transactional
	public User updateUser(Long id, User user) {
		Optional<User> optionalUser = userRepository.findById(id);
		
		if(optionalUser.isPresent()) {
			User existingUser = optionalUser.get();
			existingUser.setName(user.getName());
			existingUser.setEmail(user.getEmail());
			return userRepository.save(existingUser);
		} else {
			throw new RuntimeException("User not found");
		}
	}
	
	@Transactional(readOnly = true)
    public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public User getUserById(long id) {
	    return userRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("User with ID " + id + " not found"));
	}
	
	@Transactional(readOnly = true)
    public Page<User> getUserPaginated(int page) {
		return userRepository.findAll(PageRequest.of(page, 10));
	}
	
	public String callExternalApi() {
        RestTemplate restTemplate = new RestTemplate();
        String externalApiUrl = "https://jsonplaceholder.typicode.com/users/1";
        return restTemplate.getForObject(externalApiUrl, String.class);
    }
}
