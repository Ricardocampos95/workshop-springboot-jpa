package com.campostech.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.campostech.course.entities.User;
import com.campostech.course.repositories.UserRepository;
import com.campostech.course.services.exceptions.DbException;
import com.campostech.course.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public User findById(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public void removeById(Long id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		}catch (DataIntegrityViolationException e) {
			throw new DbException(e.getMessage());
		}
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
		try {
			User entity = userRepository.getReferenceById(id);
			updateData(entity, obj);
			return userRepository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
		
		
	}
	
	public void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
	
	
	
	
}
