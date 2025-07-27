package com.campostech.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campostech.course.entities.User;
import com.campostech.course.exceptions.DbException;
import com.campostech.course.exceptions.ResourceNotFoundException;
import com.campostech.course.repositories.UserRepository;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = userRepository.findById(id);
		return obj.get();
	}
	
	public void removeById(Long id) {
		if (!userRepository.existsById(id)) {
			throw new ResourceNotFoundException("User not found! id: " + id);
		}
		userRepository.deleteById(id);
	}
	
	public User insert(User obj) {
		List<User> list = findAll();
		
		for (User existing : list) {
			if (existing.getEmail().equals(obj.getEmail())) {
				throw new DbException("Email already exists!" + obj.getEmail());
			}
		}
		return userRepository.save(obj);
	}
	
	public User update(User obj, Long id) {
		User entity = userRepository.getReferenceById(id);
		updateData(entity, obj);
		return userRepository.save(entity);
	}
	
	public void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
	
	
	
	
}
