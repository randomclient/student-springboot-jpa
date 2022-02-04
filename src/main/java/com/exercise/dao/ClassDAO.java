package com.exercise.dao;

import java.util.List;

import com.exercise.dto.ClassDto;

public interface ClassDAO {
	public int insert(ClassDto dto);

	public ClassDto selectOne(ClassDto dto);

	public List<ClassDto> select(ClassDto dto);
}
