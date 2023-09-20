package bookreview.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bookreview.model.BookReview;
import jdbc.conn.JdbcUtil;

public class BookReviewDAO {
	
	PreparedStatement pstmt = null; 
	Statement stmt = null;
	ResultSet rs = null;
	//목록
	public List<BookReview> select(Connection conn,int startRow, int size) throws SQLException {
		
		
		List<BookReview> bookReviewList = new ArrayList<BookReview>();
		
		String sql = 
				"select B_NO,M_NO,BOOK_TITLE,AUTHOR,PUBLISHER,B_WRITER_ID,B_WRITER_NAME,B_TITLE,B_CONTENT,REGDATE,B_CNT " + 
				"from bookreview " + 
				"order by B_NO desc LIMIT ?,?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				// 생성자 이용하여 필드값 setting
				BookReview bookreview = converBookReview(rs);
				
				
				bookReviewList.add(bookreview); //목록에 추가
			}
			
			}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			}
			return bookReviewList;
		}
	private BookReview converBookReview(ResultSet rs) throws SQLException {
		// B_NO,M_NO,BOOK_TITLE,AUTHOR,PUBLISHER,B_WRITER_ID,B_WRITER_NAME,B_TITLE,B_CONTENT,REGDATE,B_CNT
		return new BookReview(rs.getInt("B_NO"),
						  rs.getInt("M_NO"),
						  rs.getString("BOOK_TITLE"),
						  rs.getString("AUTHOR"),
						  rs.getString("PUBLISHER"),
						  rs.getString("B_WRITER_ID"), 
						  rs.getString("B_WRITER_NAME"),
						  rs.getString("B_TITLE"), 
						  rs.getString("B_CONTENT"),
						  toDate(rs.getTimestamp("REGDATE")), 
						  rs.getInt("B_CNT"));
								
		
	}
	
	private Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}
	public int selectCount(Connection conn) throws SQLException  {
		
		String sql = "select count(B_NO) " +
					 "from bookreview";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {				
				return rs.getInt(1);
			}
			
			
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return 0;
	}
	//상세보기
	public BookReview selectById(Connection conn, int no) throws SQLException {
		
		String sql =	"select B_NO, M_NO, BOOK_TITLE,AUTHOR,PUBLISHER, B_WRITER_ID, B_WRITER_NAME, B_TITLE, B_CONTENT, REGDATE, B_CNT " + 
						"from bookreview " + 
						"where B_NO=? ";
		
		BookReview bookreview = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bookreview = converBookReview(rs);											// 오류
			}
			
			
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return bookreview;
	}
	//조회수
	public void increaseB_cnt(Connection conn, int no) throws SQLException {
			
		String sql = "update bookreview " + 
					 "set B_CNT=B_CNT+1 " + 
					 "where B_NO=?";
			
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,no);
			int bCnt = pstmt.executeUpdate();
			System.out.println("��ȸ�� ������ row�� = " + bCnt);	
			} finally {
			JdbcUtil.close(pstmt);			
			}
		}
	//글쓰기
		public BookReview insert(Connection conn, BookReview bookreview) throws SQLException {
			
			String sql = "insert into bookreview(M_NO,BOOK_TITLE,AUTHOR,PUBLISHER,B_WRITER_ID,B_WRITER_NAME,B_TITLE,B_CONTENT,REGDATE,B_CNT) " + 
						  "values(1,?,?,?,?,?,?,?,?,0)";
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, bookreview.getbookTitle());
				pstmt.setString(2, bookreview.getauthor());
				pstmt.setString(3, bookreview.getpublisher());
				pstmt.setString(4, bookreview.getbWriterId());
				pstmt.setString(5, bookreview.getbWriterName());
				pstmt.setString(6, bookreview.getbTitle());
				pstmt.setString(7, bookreview.getbContent());
				pstmt.setTimestamp(8, toTimestamp(bookreview.getregDate()));
				
				int cnt = pstmt.executeUpdate();
				System.out.println("insert��� �� ���� ="+cnt);
				
				if(cnt>0) {  
					stmt = conn.createStatement();
					rs = stmt.executeQuery("select last_insert_id() from bookreview");
					
					if(rs.next()) {  
						int newNum = rs.getInt(1);
						return new BookReview ( newNum,   
											1,
											bookreview.getbookTitle(),
											bookreview.getauthor(),
											bookreview.getpublisher(),
											bookreview.getbWriterId(),
											bookreview.getbWriterName(),
											bookreview.getbTitle(),
											bookreview.getbContent(),
											bookreview.getregDate(), 
											0);
					}
				}	
				
			}finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(stmt);
				JdbcUtil.close(pstmt);
			}
			return bookreview;
		}
		private Timestamp toTimestamp(Date date) {	
			return new Timestamp(date.getTime());
		}	
	//수정
	   public void update(Connection conn,String bookTitle,String author,String publisher ,String bTitle, String bContent,int no) throws SQLException {
			
			String sql = "update bookreview " + 
						 "set BOOK_TITLE=?,AUTHOR=?,PUBLISHER=?,B_TITLE=?,B_CONTENT=?,REGDATE=now() " + 
						 "where B_NO=?";
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, bookTitle);
				pstmt.setString(2, author);
				pstmt.setString(3, publisher);
				pstmt.setString(4, bTitle);
				pstmt.setString(5, bContent);
				pstmt.setInt(6, no);
				int cnt = pstmt.executeUpdate();
			} finally {
				JdbcUtil.close(pstmt);			
			}
		}
	//삭제
	   public int deleteBookReview(Connection conn, int no) {
			
			String sql = "delete from bookreview " + 
						 "where B_NO=?";
			
			int cnt = 0; 
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, no);
				cnt = pstmt.executeUpdate();
				
				return cnt;
				
			}catch (SQLException e) {
				e.printStackTrace();
				
			}finally {
				JdbcUtil.close(pstmt);
			}
			return cnt;
		}

}















