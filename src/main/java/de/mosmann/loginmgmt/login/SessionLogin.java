package de.mosmann.loginmgmt.login;

import java.io.Serializable;

import org.apache.wicket.MetaDataEntry;
import org.apache.wicket.MetaDataKey;
import org.apache.wicket.Session;

import com.google.common.base.Optional;

public class SessionLogin {

	static final MetaDataKey<Data> LOGIN_ID = new MetaDataKey<Data>() {};

	public static Optional<Data> currentLogin() {
		Data login = Session.get().getMetaData(LOGIN_ID);
		return Optional.fromNullable(login);
	}

	public static void setCurrentLogin(int loginId, String userName) {
		Session.get().setMetaData(LOGIN_ID, new Data(loginId, userName));
	}

	public static void logout() {
		Session.get().setMetaData(LOGIN_ID, null);
	}

	public static class Data implements Serializable {

		private final int _id;
		private final String _userName;

		public Data(int id, String userName) {
			_id = id;
			_userName = userName;
		}

		public int id() {
			return _id;
		}

		public String userName() {
			return _userName;
		}

	}
}
