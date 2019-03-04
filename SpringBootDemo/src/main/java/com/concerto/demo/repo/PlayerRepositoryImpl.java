package com.concerto.demo.repo;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.concerto.demo.model.Player;
@Repository
public class PlayerRepositoryImpl extends HibernateDaoSupport implements PlayerRepository {

	public PlayerRepositoryImpl(SessionFactory sessionFactory) {
		// TODO Auto-generated constructor stub
		setSessionFactory(sessionFactory);
	}
	@Override
	public List<Player> findByTeamId(long teamId) {
		return null;
	}
	
	public void savePlayer(Player player) {
		getHibernateTemplate().save(player);
	}

}
