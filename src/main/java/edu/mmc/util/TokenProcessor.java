package edu.mmc.util;

import javax.servlet.http.HttpSession;

public class TokenProcessor {

	private static final String TRANSACTION_TOKEN_KEY = "TRANSACTION_TOKEN_KEY";

	private static TokenProcessor instance = new TokenProcessor();

	protected TokenProcessor() {
		super();
	}

	public static TokenProcessor getInstance() {
		return instance;
	}

	public synchronized boolean isTokenValid(HttpSession session,String token) {

		if (session == null) {
			return false;
		}

		String saved = (String) session.getAttribute(TRANSACTION_TOKEN_KEY);

		if (saved == null) {
			return false;
		}


		if (token == null) {
			return false;
		}

		return saved.equals(token);
	}

	public synchronized void resetToken(HttpSession session) {
		if (session == null) {
			return;
		}
		session.removeAttribute(TRANSACTION_TOKEN_KEY);
	}

	public synchronized String saveToken(HttpSession session,String mes) {
		String token = generateToken(mes);
		if (token != null) {
			session.setAttribute(TRANSACTION_TOKEN_KEY, token);
		}
		return token;
	}

	public synchronized String generateToken(String mes) {
		return MdHex.md5(mes);
	}


}
