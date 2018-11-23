package com.zk.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zk.model.Participator;

@Component
public class PaticipatorRepoImpl implements PaticipatorRepo {

	
	@Autowired
	SessionFactory sf;

	@Transactional
	public Session getSession() {
		return sf.getCurrentSession();
	}


	@Override
	@Transactional
	public void clearAllPaticipators() {
		Session session=getSession();
		String hqlDelete = "delete Participator ";
		session.createQuery(hqlDelete).executeUpdate();
	}

	@Override
	@Transactional
	public void savePaticipators(List<Participator> participators) {
		Session session=getSession();
		for(int i=0;i<participators.size();i++) {
			session.save(participators.get(i));
		}
	}

}
