package com.exercise.dao;

import java.util.List;

import com.exercise.dto.UserDto;

public interface UserDAO {
	public int insert(UserDto dto);
	public int update(UserDto dto);
	public int delete(UserDto dto);
	public UserDto selectOne(UserDto dto);
	public List<UserDto> select();
}
