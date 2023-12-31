package recommand.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jdbc.conn.JdbcUtil;
import member.model.Member;
import recommand.domain.RecomBoard;


public class RecomBoardDAO {

	PreparedStatement pstmt = null; // insert용
	Statement stmt = null;  // 조회용
	ResultSet rs = null;

	public List<RecomBoard> selectAllSearch(Connection conn, int startRow, int rowSize, String col, String word) throws SQLException {
		
		System.out.println("DAO 내부의 selectAllSearch() 메서드의 파라미터값들 => startRow="+startRow+", rowSize="+rowSize+", col="+col+", word="+word);
		
		List<RecomBoard> boardList = new ArrayList<RecomBoard>();
		
		String sql = "";
		
		try {
			if(col.equals("all")) {  // 아이디, 제목, 내용
				
				System.out.println("col이 all일경우");
				
				sql = "SELECT a.R_NO, b.MID, b.MNAME, a.R_TITLE, a.R_CONTENT, a.LIKE_IT, a.R_CNT, a.REGDATE, a.MODDATE, a.M_NO " + 
						 	 "FROM recomboard a, member b " + 
						 	 "WHERE a.M_NO=b.M_NO AND b.MID LIKE CONCAT('%',CONCAT(?,'%')) OR a.R_TITLE LIKE CONCAT('%',CONCAT(?,'%')) " +
						 	 "OR a.R_CONTENT LIKE CONCAT('%',CONCAT(?,'%')) " + 
						 	 "order BY a.R_NO desc LIMIT ?,?";    
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, word);
				pstmt.setString(2, word);
				pstmt.setString(3, word);
				pstmt.setInt(4, startRow);  
				pstmt.setInt(5, rowSize);
	
			}else if(col.equals("searchId")) {  // 아이디
				
				System.out.println("col이 searchId일경우");
				
				sql = "SELECT a.R_NO, b.MID, b.MNAME, a.R_TITLE, a.R_CONTENT, a.LIKE_IT, a.R_CNT, a.REGDATE, a.MODDATE, a.M_NO " + 
							 "FROM recomboard a, member b " + 
//							 "WHERE a.M_NO=b.M_NO AND b.MID LIKE     '%'||?||'%' " + 
							 "WHERE a.M_NO=b.M_NO AND b.MID LIKE CONCAT('%',CONCAT(?,'%')) " + 
							 "order BY a.R_NO desc LIMIT ?,?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, word);
				pstmt.setInt(2, startRow); 
				pstmt.setInt(3, rowSize);	
					
			}else if(col.equals("searchTitle")) {  // 제목
				
				System.out.println("col이 searchTitle일경우");
				
				sql = "SELECT a.R_NO, b.MID, b.MNAME, a.R_TITLE, a.R_CONTENT, a.LIKE_IT, a.R_CNT, a.REGDATE, a.MODDATE, a.M_NO " + 
					 	 	 "FROM recomboard a, member b " + 
					 	 	 "WHERE a.M_NO=b.M_NO AND a.R_TITLE LIKE CONCAT('%',CONCAT(?,'%')) " + 
					 	 	 "order BY a.R_NO desc LIMIT ?,?";
			
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, word);
				pstmt.setInt(2, startRow);  
				pstmt.setInt(3, rowSize);	
				
				rs = pstmt.executeQuery();
				
			}else if(col.equals("searchContent")) {  // 내용
				
				System.out.println("col이 searchContent일경우");
				
				sql = "SELECT a.R_NO, b.MID, b.MNAME, a.R_TITLE, a.R_CONTENT, a.LIKE_IT, a.R_CNT, a.REGDATE, a.MODDATE, a.M_NO " + 
					 	 "FROM recomboard a, member b " + 
					 	 "WHERE a.M_NO=b.M_NO AND a.R_CONTENT LIKE CONCAT('%',CONCAT(?,'%')) " + 
					 	 "order BY a.R_NO desc LIMIT ?,?";
			
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, word);
				pstmt.setInt(2, startRow); 
				pstmt.setInt(3, rowSize);
				
			}else if(col.equals("searchTitleContent")) {  // 제목+내용
				
				System.out.println("col이 searchTitleContent일경우");
				
				sql = "SELECT a.R_NO, b.MID, b.MNAME, a.R_TITLE, a.R_CONTENT, a.LIKE_IT, a.R_CNT, a.REGDATE, a.MODDATE, a.M_NO " + 
					 	 "FROM recomboard a, member b " + 
					 	 "WHERE a.M_NO=b.M_NO AND a.R_TITLE LIKE CONCAT('%',CONCAT(?,'%')) OR a.R_CONTENT LIKE CONCAT('%',CONCAT(?,'%')) " + 
					 	 "order BY a.R_NO desc LIMIT ?,?";
			
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, word);
				pstmt.setString(2, word);
				pstmt.setInt(3, startRow); 
				pstmt.setInt(4, rowSize);
				
			} else {  // 조회조건없음 (전체목록)
				
				System.out.println("col이 없는경우. 즉 전체 목록 조회");
				
				sql = "SELECT a.R_NO, b.MID, b.MNAME, a.R_TITLE, a.R_CONTENT, a.LIKE_IT, a.R_CNT, a.REGDATE, a.MODDATE, a.M_NO " + 
						 "FROM recomboard a, member b " + 
						 "WHERE a.M_NO=b.M_NO " + 
						 "order BY a.R_NO desc LIMIT ?,?";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, startRow);  // int startRow : 시작행 index번호를 의미. 가장 첫번째행은 0부터 시작.
				pstmt.setInt(2, rowSize);	// int rowSize : 1페이지에 보여줄 글 개수			

			}
			
			rs = pstmt.executeQuery();
			System.out.println("rs = " + rs);

			while(rs.next()) {
				RecomBoard board = convertBoard(rs);

				boardList.add(board);	
			}
			
			System.out.println("boardList="+boardList);
			
			return boardList;
			
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		
	}
	

	public List<RecomBoard> selectAll(Connection conn, int startRow, int rowSize) throws SQLException {
		
		String sql = "SELECT a.R_NO, b.MID, b.MNAME, a.R_TITLE, a.R_CONTENT, a.LIKE_IT, a.R_CNT, a.REGDATE, a.MODDATE, a.M_NO " + 
					 "FROM recomboard a, member b " + 
					 "WHERE a.M_NO=b.M_NO " + 
					 "order BY a.R_NO desc LIMIT ?,?";
		
		List<RecomBoard> boardList = new ArrayList<RecomBoard>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, startRow);  // int startRow : 시작행 index번호를 의미. 가장 첫번째행은 0부터 시작.
			pstmt.setInt(2, rowSize);	// int rowSize : 1페이지에 보여줄 글 개수
			
			rs = pstmt.executeQuery();
			System.out.println("rs = " + rs);
			
			while(rs.next()) {
				RecomBoard board = convertBoard(rs);

				boardList.add(board);					
			}
			return boardList;
			
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	
	// 글쓰기
	// 매개변수 WriteRequest : Writer(로그인한 유저id, 로그인한 유저명), 입력제목, 입력내용
	public RecomBoard insert(Connection conn, RecomBoard recomBoard) throws SQLException {

		String sql = "INSERT INTO recomboard (book_title, author, publisher, r_title, r_content, LIKE_it, r_cnt, regdate, moddate, m_no) " + 
					 "VALUES (?, ?, ?, ?, ?, 0, 0, ?, ?, ?);";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, recomBoard.getBookTitle());
			pstmt.setString(2, recomBoard.getAuthor());
			pstmt.setString(3, recomBoard.getPublisher());
			pstmt.setString(4, recomBoard.getrTitle());
			pstmt.setString(5, recomBoard.getrContent());
			pstmt.setTimestamp(6, toTimestamp(recomBoard.getRegDate()));
			pstmt.setTimestamp(7, toTimestamp(recomBoard.getModDate())); 
			pstmt.setInt(8, recomBoard.getmNo()); 
			
			int cnt = pstmt.executeUpdate();
			System.out.println("게시판에 저장된  행개수 ="+cnt);
			
			if(cnt>0) {  // 입력이 되었다면
				stmt = conn.createStatement();
				rs = stmt.executeQuery("select last_insert_id() from recomboard");  //  last_insert_id() 함수 : 테이블의 마지막 auto_increment 값을 반환.
																					//                        여기에서는  recomboard 테이블의 PK인 rNo 반환.
				if(rs.next()) {
					
					int newNum = rs.getInt(1);    // getInt(1) : 컬럼의 1번째 값을 int형으로 반환
					return new RecomBoard( newNum,   // newNum : 마지막으로 insert된 rNo
										   recomBoard.getBookTitle(),
										   recomBoard.getAuthor(),
										   recomBoard.getPublisher(),
										   recomBoard.getrTitle(), 
										   recomBoard.getrContent(), 
										   recomBoard.getLikeIt(), 
										   recomBoard.getrCnt(), 
										   recomBoard.getRegDate(),
										   recomBoard.getModDate(),
										   recomBoard.getmNo()
										 );
				}
			}
			
			return recomBoard;
 
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(pstmt);
		}
	}
	
	public RecomBoard selectById(Connection conn, int no) throws SQLException {
		
		System.out.println("RecomBoardDAO 클래스의  selectById() 메서드 진입v no="+no);
		
		String sql = "SELECT a.R_NO, b.M_NO, b.MID, b.MNAME, a.BOOK_TITLE, a.AUTHOR, a.PUBLISHER, a.R_TITLE, a.R_CONTENT, a.LIKE_IT, a.R_CNT, " +
					 "a.REGDATE, a.MODDATE, a.M_NO " + 
					 "FROM recomboard a, member b " + 
					 "WHERE a.M_NO=b.M_NO AND a.R_NO=?";
		
		RecomBoard recomBoard = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				recomBoard = new RecomBoard(
											rs.getInt("a.R_NO"),
											new Member(rs.getInt("b.M_NO"),  rs.getString("b.MID"), rs.getString("b.MNAME")),
											rs.getString("a.BOOK_TITLE"), 
											rs.getString("a.AUTHOR"), 
											rs.getString("a.PUBLISHER"), 
										    rs.getString("a.R_TITLE"), 
										    rs.getString("a.R_CONTENT"), 
										    rs.getInt("a.LIKE_IT"), 
										    rs.getInt("a.R_CNT"), 
										    toDate(rs.getTimestamp("a.REGDATE")), 
										    toDate(rs.getTimestamp("a.MODDATE")), 
										    rs.getInt("a.M_NO")
									 	   );
				System.out.println("DAO에서 생성된 recomBoard 객체 = " + recomBoard);
			}
			return recomBoard;
			
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public void update(Connection conn, String bookTitle, String author, String publisher, 
			String rTitle, String rContent, int rNo, int mNo) throws SQLException {
		
		System.out.println("RecomBoard DAO 클래스의 update() 메서드 진입");
		System.out.println("RecomBoard DAO 클래스의 update() 메서드에서 입력한 데이터 ==> bookTitle="+bookTitle+", author="+author +
				", publisher="+publisher+", rTitle="+rTitle+", rContent="+rContent + ", rNo="+rNo + ", mNo="+mNo);
		
		String sql = "update recomboard " + 
					 "set book_title=?, author=?, publisher=?, r_title=?, r_content=?, moddate=now() " + 
					 "where r_no=? AND m_no=?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bookTitle);
			pstmt.setString(2, author);
			pstmt.setString(3, publisher);
			pstmt.setString(4, rTitle);
			pstmt.setString(5, rContent);
			pstmt.setInt(6, rNo);
			pstmt.setInt(7, mNo);
			int cnt = pstmt.executeUpdate();
			System.out.println("update된 게시판 행개수 = " + cnt);
			
		} finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	public int delete(Connection conn, int no) {
		
		String sql = "DELETE from recomboard " + 
					 "WHERE r_no=?";
		
		int cnt = 0;  // 삭제(update용)된 행 갯수를 저장할 변수
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			cnt = pstmt.executeUpdate();
			System.out.println("삭제된 게시판 행 개수="+cnt);
			
			return cnt;
			
		}catch (SQLException e) {
			e.printStackTrace();
			
		}finally {
			JdbcUtil.close(pstmt);
		}
		
		return cnt;
	}
	
	// RecomBoard 객체로 변환
	private RecomBoard convertBoard(ResultSet rs) throws SQLException {
		
		return new RecomBoard(rs.getInt("R_NO"), 
							  new Member(rs.getString("MID"), rs.getString("MNAME")),
							  rs.getString("R_TITLE"), 
							  rs.getString("R_CONTENT"),
							  rs.getInt("LIKE_IT"),
							  rs.getInt("R_CNT"),
							  toDate(rs.getTimestamp("REGDATE")), 
							  toDate(rs.getTimestamp("MODDATE")),
							  rs.getInt("M_NO"));

	}
	
	public int selectCount(Connection conn) throws SQLException  {
		
		String sql = "select count(R_NO) " +
					 "from recomboard";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {				
				return rs.getInt(1);  // count란 컬럼이 없기때문에 조회해서 index 값을 준다. 
			}
			return 0;
			
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int selectSearchCount(Connection conn, String col, String word) throws SQLException  {
		
		System.out.println("RecomBoardDAO클래스의  selectSearchCount() 메서드 진입");
		//  SELECT COUNT(*) cnt FROM recomboard WHERE r_title LIKE CONCAT('%',CONCAT('title','%'));
		
		String sql = "";
		
		try {
			if(col.equals("all")) {  // 아이디, 게시판 제목, 게시판 내용
			
				System.out.println("col이 all일경우");
				
				sql = "SELECT COUNT(*) cnt " + 
						  "FROM recomboard a, member b " + 
						  "WHERE a.M_NO=b.M_NO AND " +
						  "b.MID LIKE CONCAT('%',CONCAT(?,'%')) OR " +
						  "a.R_TITLE LIKE CONCAT('%',CONCAT(?,'%')) OR " +
						  "a.R_CONTENT LIKE CONCAT('%',CONCAT(?,'%'))";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, word);
				pstmt.setString(2, word);
				pstmt.setString(3, word);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {				
					return rs.getInt(1);  // count란 컬럼이 없기때문에 조회해서 index 값을 준다. 
				}
				return 0;
				
			}else if(col.equals("searchId")) {  // 아이디
	
				System.out.println("col이 searchId일경우");	
			 
				sql = "SELECT COUNT(*) cnt " + 
					  "FROM recomboard a, member b " + 
					  "WHERE a.M_NO=b.M_NO and b.MID LIKE CONCAT('%',CONCAT(?,'%'))";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, word);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {				
					return rs.getInt(1);  
				}
				return 0;
						
			}else if(col.equals("searchTitle")) {  // 게시판 제목
			
				System.out.println("col이 searchTitle일경우");		
				
				sql = "SELECT COUNT(*) cnt " + 
					  "FROM recomboard a, member b " + 
					  "WHERE a.M_NO=b.M_NO and a.R_TITLE LIKE CONCAT('%',CONCAT(?,'%'))";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, word);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {				
					return rs.getInt(1);  
				}
				return 0;
				
			}else if(col.equals("searchContent")) {   // 게시판 내용
				
				System.out.println("col이 searchContent일경우");	
				
				sql = "SELECT COUNT(*) cnt " + 
					  "FROM recomboard a, member b " + 
					  "WHERE a.M_NO=b.M_NO and a.R_CONTENT LIKE CONCAT('%',CONCAT(?,'%'))";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, word);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {				
					return rs.getInt(1);  
				}
				return 0;
			
			}else if(col.equals("searchTitleContent")) {  // 게시판 제목, 게시판 내용
				
				System.out.println("col이 searchTitleContent일경우");
				
				sql = "SELECT COUNT(*) cnt " + 
					  "FROM recomboard a, member b " + 
					  "WHERE a.M_NO=b.M_NO AND a.R_TITLE LIKE CONCAT('%',CONCAT(?,'%')) " + 
					  "OR a.R_CONTENT LIKE CONCAT('%',CONCAT(?,'%'))";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, word);
				pstmt.setString(2, word);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {				
					return rs.getInt(1);  
				}
				return 0;
			
			}else {  // 조회조건없음 (전체목록)
				
				System.out.println("조회조건 없을경우 (전체목록)");
				
				sql = "SELECT COUNT(*) cnt " + 
					  "FROM recomboard a, member b " + 
					  "WHERE a.M_NO=b.M_NO";
				
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {				
					return rs.getInt(1);  
				}
				return 0;
			}
		
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}	
	}
	
	
	// 자바 필드의 Date타입을 DB의 Timestamp 타입으로 변환하기
	private Timestamp toTimestamp(Date date) {
		
		return new Timestamp(date.getTime());
	}
	
	// Timestamp -> Date 타입으로 변환하기
	private Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}
	
	// 조회수 증가
	public void increaseReadCount(Connection conn, int no) throws SQLException {
		
		String sql = "update recomboard " + 
					 "SET r_cnt=r_cnt+1 " + 
					 "where r_no=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			int cnt = pstmt.executeUpdate();
			System.out.println("증가한 조회수 row = " + cnt);
			
		} finally {
			JdbcUtil.close(pstmt);			
		}
	}
}
