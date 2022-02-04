package com.exercise.dao;

import java.util.List;

import com.exercise.dto.StudentDto;

public interface StudentDAO {
	public int insert(StudentDto dto);

	public int update(StudentDto dto);

	public int delete(StudentDto dto);

	public StudentDto selectOne(StudentDto dto);

	public List<StudentDto> select();}
