package com.exercise.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.exercise.dto.StudentDto;
import com.exercise.service.JpaUtil;

@Repository
public class StudentDAOImpl implements StudentDAO {

	@Override
	public int insert(StudentDto dto) {
		EntityManager em = null;
		int result = 0;

		try {
			em = JpaUtil.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();
			em.persist(dto);
			em.getTransaction().commit();
			result = 1;
		} finally {
			em.close();
		}
		return result;
	}

	@Override
	public int update(StudentDto dto) {
		EntityManager em = null;
		int result = 0;

		try {
			em = JpaUtil.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();
			em.merge(dto);
			em.getTransaction().commit();
			result = 1;
		} finally {
			em.close();
		}
		return result;
	}

	@Override
	public int delete(StudentDto dto) {
		EntityManager em = null;
		int result = 0;

		try {
			em = JpaUtil.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();
			StudentDto output = em.find(StudentDto.class, dto.getStudentId());
			em.remove(output);
			em.getTransaction().commit();
			result = 1;
		} finally {
			em.close();
		}
		return result;
	}

	@Override
	public StudentDto selectOne(StudentDto dto) {
		EntityManager em = null;
		StudentDto student = new StudentDto();
		try {
			em = JpaUtil.getEntityManagerFactory().createEntityManager();
			student = em.find(StudentDto.class, dto.getStudentId());
		} finally {
			em.close();
		}
		return student;
	}

	@Override
	public List<StudentDto> select() {
		EntityManager em = null;
		List<StudentDto> stulist = new ArrayList<StudentDto>();

		try {
			em = JpaUtil.getEntityManagerFactory().createEntityManager();
			stulist = em.createQuery("select s from StudentDto s").getResultList();
		} finally {
			em.close();
		}
		return stulist;
	}

}
