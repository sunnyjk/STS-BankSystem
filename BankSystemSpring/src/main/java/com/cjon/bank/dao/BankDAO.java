package com.cjon.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cjon.bank.dto.BankDTO;
import com.cjon.bank.util.DBTemplate;

public class BankDAO {
	
	private DBTemplate template;
	
	public BankDAO() {
	}
	
	public DBTemplate getTemplate() {
		return template;
	}

	public void setTemplate(DBTemplate template) {
		this.template = template;
	}

	public BankDTO update(BankDTO dto) {
		
		Connection con = template.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {			
			String sql = "update bank set balance = balance+? where userid = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getBalance());
			pstmt.setString(2, dto.getUserid());
			
			int count = pstmt.executeUpdate();	
			
			if(count == 1){	// 정상처리
				String sql1 = "select userid, balance from bank where userid=?";
				PreparedStatement pstmt1 = con.prepareStatement(sql1);
				pstmt1.setString(1, dto.getUserid());	// IN Parameter 처리
				rs = pstmt1.executeQuery();
				
				if(rs.next()){
					dto.setBalance(rs.getInt("balance"));
				}
				
				dto.setResult(true);	// 정상처리 되었다는 결과 저장
				
				try{
					rs.close();
					pstmt1.close();
				} catch(Exception e){
					e.printStackTrace();
				}
				
			} else{
				dto.setResult(false);	// 비정상처리
			}
			
		} catch(Exception e){
			System.out.println(e);
		} finally {
			try {
				pstmt.close();
				// con.close(); 얘는 트랜잭션이 끝난 후, 서비스에서 처리해 줄꺼야.
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return dto;
	}

	public BankDTO updateWithdraw(BankDTO dto) {
		Connection con = template.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "update bank set balance = balance-? where userid = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dto.getBalance());
			pstmt.setString(2, dto.getUserid());
			
			int count = pstmt.executeUpdate();	
			
			if(count == 1){	// 정상처리
				String sql1 = "select userid, balance from bank where userid=?";
				PreparedStatement pstmt1 = con.prepareStatement(sql1);
				pstmt1.setString(1, dto.getUserid());	// IN Parameter 처리
				rs = pstmt1.executeQuery();
				
				if(rs.next()){
					dto.setBalance(rs.getInt("balance"));
				}
				if(dto.getBalance() < 0){
					dto.setResult(false);
					System.out.println("예금금액이 작아서 출금할 수 없습니다.");
				} else{
					dto.setResult(true);
					System.out.println("commit.");
				}
				
				try{
					rs.close();
					pstmt1.close();
				} catch(Exception e){
					e.printStackTrace();
				}
				
			}		
			
		} catch(Exception e){
			System.out.println(e);
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return dto;
	}

}
