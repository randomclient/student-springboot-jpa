package com.exercise.service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
	static EntityManagerFactory emfactory = null;

	public static EntityManagerFactory getEntityManagerFactory() {
		return emfactory = Persistence.createEntityManagerFactory("sb_th_jpa");
	}
}
