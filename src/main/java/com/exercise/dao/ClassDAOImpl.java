package com.exercise.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.exercise.dto.ClassDto;
import com.exercise.service.JpaUtil;

@Repository
public class ClassDAOImpl implements ClassDAO {

	@Override
	public int insert(ClassDto dto) {
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
	public ClassDto selectOne(ClassDto dto) {
		EntityManager em = null;
		ClassDto output = new ClassDto();

		try {
			em = JpaUtil.getEntityManagerFactory().createEntityManager();
			output = em.find(ClassDto.class, dto.getId());
		} finally {
			em.close();
		}
		return output;
	}

	@Override
	public List<ClassDto> select(ClassDto dto) {
		EntityManager em = null;
		List<ClassDto> output = new ArrayList<>();

		try {
			em = JpaUtil.getEntityManagerFactory().createEntityManager();
			output = em.createQuery("select c from ClassDto c").getResultList();
		} finally {
			em.close();
		}
		return output;
	}

}
