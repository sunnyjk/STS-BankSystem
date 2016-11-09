package com.cjon.bank.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBTemplate {

	private Connection con;

	public DBTemplate() {
		try {

			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/library";
			String user = "jQuery";
			String password = "jQuery";
			con = DriverManager.getConnection(url, user, password);
			
			// Transaction의 시작
			con.setAutoCommit(false);
			// default값이 true로 설정되어 있다.
			// con에 대해 commit 되거나 rollback 되거나
			// 혹은 정상적으로 connection이 close될 때 transaction이 종료(rollback 처리됨).

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void commit() {
		try {
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void rollback() {
		try {
			con.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

}
