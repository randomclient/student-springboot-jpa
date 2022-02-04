package com.exercise.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;
import com.exercise.dto.UserDto;
import com.exercise.service.JpaUtil;

@Repository
public class UserDAOImpl implements UserDAO {

	@Override
	public int insert(UserDto dto) {
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
	public int update(UserDto dto) {
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
	public int delete(UserDto dto) {
		EntityManager em = null;
		int result = 0;

		try {
			em = JpaUtil.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();
			UserDto user = em.find(UserDto.class, dto.getId());
			em.remove(user);
			em.getTransaction().commit();
			result = 1;
		} finally {
			em.close();
		}
		return result;
	}

	@Override
	public UserDto selectOne(UserDto dto) {
		EntityManager em = null;
		UserDto output = new UserDto();

		try {
			em = JpaUtil.getEntityManagerFactory().createEntityManager();
			output = em.find(UserDto.class, dto.getId());
		} finally {
			em.close();
		}
		return output;
	}

	@Override
	public List<UserDto> select() {
		EntityManager em = null;
		List<UserDto> userlist = new ArrayList<>();
		try {
			em = JpaUtil.getEntityManagerFactory().createEntityManager();
			userlist = em.createQuery("select u from UserDto u").getResultList();
		} finally {
			em.close();
		}
		return userlist;
	}

}
