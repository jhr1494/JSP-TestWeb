package com.testweb.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.testweb.util.JdbcUtil;

public class BoardDAO {
	
	private DataSource ds;
	private static BoardDAO instance = new BoardDAO();
	
	private BoardDAO() {
		
		try {
			InitialContext ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		} catch (NamingException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public static BoardDAO getInstance() {
		return instance;
		
	}
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	//기능별 메서드
	
	//글등록
	public void write(String wrietr, String title, String content) {
		String sql = "insert into board_test(bno, writer, title, content)"
				+ "values(board_test_seq.nextval, ?, ?, ?)";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, wrietr);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, null);
			
		}
	}
	
	
	//글 상세보기
	public BoardVO getContent(String bno) {
		BoardVO vo = new BoardVO();
		String sql = "select * from board_test where bno = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bno);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo.setBno(rs.getInt("bno"));
				vo.setRagdate(rs.getTimestamp("ragdate"));
				vo.setWriter(rs.getString("writer"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		
		
		return vo;
	}
	
	
	//목록조회
	public ArrayList<BoardVO> getList(int pNum, int amount) {
		ArrayList<BoardVO> list = new ArrayList<>();
		
		String sql = "select * from(select \r\n" + 
				"                rownum rn,\r\n" + 
				"                bno,\r\n" + 
				"                writer,\r\n" + 
				"                title,\r\n" + 
				"                content,\r\n" + 
				"                ragdate\r\n" + 
				"                from( SELECT * FROM\r\n" + 
				"                        board_test ORDER BY bno desc)\r\n" + 
				"            ) where rn > ? and rn <= ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (pNum-1) * amount);
			pstmt.setInt(2, pNum * amount);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int bno = rs.getInt("bno");
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp ragdate = rs.getTimestamp("ragdate");
				
				BoardVO vo = new BoardVO(bno, writer, title, content, ragdate);
				
				list.add(vo);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
			
		}
		
		return list;
		
	}
	
	
	//전체게시글 수 확인
	public int getTotal() {
		int total = 0;
		String sql = "select count(*) as total from board_test";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				total = rs.getInt("total");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
			
		}
		
		
		return total;
	}
	
	
	//글 수정
	public void update(String bno, String title, String content) {
		
		String sql = "update board_test set title = ?, content = ? where bno = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, bno);
			
			int result = pstmt.executeUpdate();
			
			if(result ==1) {
				System.out.println("수정성공");
			}else {
				System.out.println("수정실패");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, null);
			
		}
		
		
	}
	
	
	//글 삭제
	public void delete(String bno) {
		
		String sql = "delete from board_test where bno = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bno);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
			
		}
		
		
		
	}
	
	//유저리스트
	public ArrayList<BoardVO> UserList(int pNum, int amount, String id) {
		ArrayList<BoardVO> userList = new ArrayList<>();
		
		
		String sql = "SELECT * FROM (\r\n" + 
				"SELECT rownum rn, a.*\r\n" + 
				"from(SELECT b.* \r\n" + 
				"FROM board_test b\r\n" + 
				"INNER join user_test u on u.id = b.writer\r\n" + 
				"where u.id = ? order by b.bno desc) a\r\n" + 
				")WHERE rn > ? and rn <= ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, (pNum-1) * amount);
			pstmt.setInt(3, pNum * amount);
			System.out.println(id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int bno = rs.getInt("bno");
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Timestamp ragdate = rs.getTimestamp("ragdate");
				
				BoardVO vo = new BoardVO(bno, writer, title, content, ragdate);
				
				userList.add(vo);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
			
		}
		
		return userList;
		
	}
	
	//회원 게시글 수 확인
		public int getUserTotal(String id) {
			int total = 0;
		
			String sql = "select count(*) as total from board_test where writer = ?";
			
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					total = rs.getInt("total");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JdbcUtil.close(conn, pstmt, rs);
				
			}
			
			
			return total;
		}
	

}
