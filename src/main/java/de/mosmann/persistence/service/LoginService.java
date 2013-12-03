package de.mosmann.persistence.service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

import de.mosmann.loginmgmt.events.NewLogin;

public class LoginService implements ILoginService {

	ConcurrentHashMap<Integer, Login> map = new ConcurrentHashMap<>();
	AtomicInteger counter = new AtomicInteger();

	@Override
	public Login getById(int id) {
		return new Login(map.get(id));
	}
	
	@Override
	public void save(Login login) {
		map.put(login.getId(), login);
	}
	
	@Override
	public List<Login> findAll() {
		return Lists.transform(Lists.newArrayList(map.values()),new Function<Login, Login>() {
			@Override
			public Login apply(Login input) {
				return new Login(input);
			}
		});
	}
	
	@Override
	public int create(NewLogin login) {
		int cur = counter.incrementAndGet();
		Login toStore = new Login();
		toStore.setEmail(login.getEmail());
		toStore.setName(login.getName());
		toStore.setPasswd(login.getPasswd());
		toStore.setId(cur);
		map.put(cur, toStore);
		return cur;
	}
}
