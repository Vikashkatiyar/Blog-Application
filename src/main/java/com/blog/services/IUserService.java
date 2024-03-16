package com.blog.services;

import java.util.List;

import com.blog.payloads.UserDto;

public interface IUserService {
   
	UserDto createUser(UserDto userDto);
	
	UserDto updateUser(UserDto userDto, Integer userId);
	
	UserDto getUserById(Integer userId);
	
	List<UserDto> getAllUsers();
	
	void deleteUser(Integer userId);
}
