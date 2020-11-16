package com.testweb.user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.testweb.util.JdbcUtil;

import javafx.util.converter.DateStringConverter;

public class UserDAO {
	
	private DataSource ds;
	
	private Connection conn = null;
	private PreparedStatement pstmt= null;
	private ResultSet rs = null;
	
	//싱글톤
	private static UserDAO instance = new UserDAO();
	
	private UserDAO() {
		//드라이버로드 - 커넥션 풀
		try {
			InitialContext ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle");
			
			
		} catch (NamingException e) {
			e.printStackTrace();
		}	
	}
	
	public static UserDAO getInstance() {
		return instance;
	}
	
	
	
	//기능별 메서드
	
	//id중복검사
	public int checkId(String id) {
		int result = 0;
		
		String sql = "select * from user_test where id = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = 1;
			}else {
				result = 0;
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return result;
		
	}
	
	//회원가입
	public int join(UserVO vo) {
		int result = 0;
		String sql = "insert into user_test(id, pw, name, phone, email, address_basic, address_detail) values(?,?,?,?,?,?,?) ";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getPhone());
			pstmt.setString(5, vo.getEmail());
			pstmt.setString(6, vo.getAddress_basic());
			pstmt.setString(7, vo.getAddress_detaile());
			
			result =  pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, null);
		}
		
		return result;
	}
	
	//로그인
	public UserVO login(String id, String pw) {
		UserVO  vo = null;
		
		String sql = "select * from user_test where id = ? and pw = ? ";
		
//		System.out.println("id : " + id);
//		System.out.println("pw : " + pw);
		
		try {
			conn=ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new UserVO();
				vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));
				vo.setPhone(rs.getString("phone"));
				vo.setEmail(rs.getString("email"));
				vo.setAddress_basic(rs.getString("address_basic"));
				vo.setAddress_detaile(rs.getString("address_detail"));
				
				
				
			}else {
				vo = null;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return vo;
	}
	
	
	//회원수정
	public int update(UserVO vo) {
		int result = 0;
		
		String sql = "UPDATE user_test SET "
				+ "pw = ?, name = ?, phone = ?, email = ?, address_basic = ?, address_detail = ? "
				+ "where id = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPw());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getPhone());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getAddress_basic());
			pstmt.setString(6, vo.getAddress_detaile());
			pstmt.setString(7, vo.getId());
			
			result = pstmt.executeUpdate();

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
			
		}
		
		return result;
		
	}

	//회원탈퇴
	public int delete(String id) {
		int result = 0;
		
		String sql = "DELETE FROM user_test WHERE id = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
			
		}

		
		return result;
	}

}//end class
