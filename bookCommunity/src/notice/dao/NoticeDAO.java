package notice.dao;

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
import notice.model.Notice;
import notice.model.dto.Writer;

public class NoticeDAO {
	
	PreparedStatement pstmt = null; 
	Statement stmt = null;
	ResultSet rs = null;
	//목록
	public List<Notice> select(Connection conn,int startRow, int size) throws SQLException {
		String sql = 
				"select N_NO,M_NO,N_WRITERID,N_WRITERNAME,N_TITLE, " + 
				"N_CONTENT,REGDATE,N_CNT " + 
				"from  notice " +
				"order by REGDATE desc limit ?,?";
		
		List<Notice> noticeList = new ArrayList<Notice>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Notice notice = converNotice(rs);
				 noticeList.add(notice);
			}
			return noticeList;
			}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			}
		}
	private Notice converNotice(ResultSet rs) throws SQLException {
		
		return new Notice(rs.getInt("n_no"),
						  rs.getInt("m_no"),
			   new Writer(rs.getString("n_writerid"), rs.getString("n_writername")),
						  rs.getString("n_title"), 
						  rs.getString("n_content"),
						  toDate(rs.getTimestamp("regdate")), 
						  rs.getInt("n_cnt"));
	}
	private Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}
	public int selectCount(Connection conn) throws SQLException  {
		
		String sql = "select count(N_NO) " +
					 "from notice";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {				
				return rs.getInt(1);
			}
			return 0;
			
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	//검색
		public List<Notice> search(Connection conn,String choice,String searchText,int startRow, int size)throws SQLException{
			
			List<Notice> searchList = new ArrayList<Notice>();
			
			String sql = "select N_NO,M_NO,N_WRITERID,N_WRITERNAME,N_TITLE,N_CONTENT,REGDATE,N_CNT " + 
			   			 "from  notice ";
			
			String sqlWord="";
			
				if(choice.equals("all")) {
					sqlWord = " where N_TITLE or N_CONTENT LIKE concat(concat('%',?),'%') order by REGDATE desc limit ?,?";
				}else if(choice.equals("title")) {
					sqlWord = " where N_TITLE LIKE concat(concat('%',?),'%') order by REGDATE desc limit ?,?";
				}else if(choice.equals("content")) {
					sqlWord = " where N_CONTENT LIKE concat(concat('%',?),'%') order by REGDATE desc limit ?,?";
				}
				 sql = sql + sqlWord;
			try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, searchText.trim());
					pstmt.setInt(2, startRow);
					pstmt.setInt(3, size);
					rs = pstmt.executeQuery();
				
				while(rs.next()) {
					Notice notice = searchNotice(rs);
					searchList.add(notice);
				}
			}finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
			return searchList;
		}
		private Notice searchNotice(ResultSet rs) throws SQLException {
			
			Notice sn = new Notice(
						rs.getInt("n_no"),
						rs.getInt("m_no"),
						new Writer(rs.getString("n_writerid"), rs.getString("n_writername")),
						rs.getString("n_title"), 
						rs.getString("n_content"),
						toDate(rs.getTimestamp("regdate")), 
						rs.getInt("n_cnt"));
			
			return sn;
		}
		public int searchCount(Connection conn,String choice,String searchText) throws SQLException  {
			
			String sql = "select count(N_NO) " + 
						 "from  notice";
			
			String sqlWord="";
			
			if(choice.equals("all")) {
				sqlWord = " where N_TITLE or N_CONTENT LIKE concat(concat('%',?),'%')";
			}else if(choice.equals("title")) {
				sqlWord = " where N_TITLE LIKE concat(concat('%',?),'%')";
			}else if(choice.equals("content")) {
				sqlWord = " where N_CONTENT LIKE concat(concat('%',?),'%')";
			}
			
			sql = sql + sqlWord;
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, searchText.trim());
				rs = pstmt.executeQuery();
				if(rs.next()) {
					return rs.getInt(1);
				}
				return 0;
			}finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(pstmt);
			}
		}
	//상세보기
	public Notice selectById(Connection conn, int no) throws SQLException {
		
		String sql = "select N_NO,M_NO,N_WRITERID,N_WRITERNAME,N_TITLE,N_CONTENT,REGDATE,N_CNT " + 
					 "from notice " + 
					 "where N_NO=?";
		
		Notice notice = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				notice = converNotice(rs);
			}
			return notice;
			
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	//조회수
	public void increaseN_cnt(Connection conn, int no) throws SQLException {
			
		String sql = "update notice " + 
					 "set N_CNT=N_CNT+1 " + 
					 "where N_NO=?";
			
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,no);
			int n_cnt = pstmt.executeUpdate();
			} finally {
			JdbcUtil.close(pstmt);			
			}
		}
	//글쓰기
		public Notice insert(Connection conn, Notice notice) throws SQLException {
			
			String sql = "insert into notice(M_NO,N_WRITERID,N_WRITERNAME,N_TITLE,N_CONTENT,REGDATE,N_CNT)" + 
						  "values(9999,?,?,?,?,?,0)";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, notice.getN_writer().getN_writerid());
				pstmt.setString(2, notice.getN_writer().getN_writername());
				pstmt.setString(3, notice.getN_title());
				pstmt.setString(4, notice.getN_content());
				pstmt.setTimestamp(5, toTimestamp(notice.getRegdate()));
				
				int cnt = pstmt.executeUpdate();
				System.out.println("insert��� �� ���� ="+cnt);
				
				if(cnt>0) {  
					stmt = conn.createStatement();
					rs = stmt.executeQuery("select last_insert_id() from notice");
					
					if(rs.next()) {  
						int newNum = rs.getInt(1);
						return new Notice ( newNum,   
											9999,
											notice.getN_writer(), 
											notice.getN_title(),
											notice.getN_content(),
											notice.getRegdate(), 
											0);
					}
				}	
				return null;
			}finally {
				JdbcUtil.close(rs);
				JdbcUtil.close(stmt);
				JdbcUtil.close(pstmt);
			}
		}
		private Timestamp toTimestamp(Date date) {	
			return new Timestamp(date.getTime());
		}	
	//수정
	   public void update(Connection conn,String n_title, String n_content,int no) throws SQLException {
			
			String sql = "update notice " + 
						 "set N_TITLE=?,N_CONTENT=?,REGDATE=now() " + 
						 "where N_NO=?";
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, n_title);
				pstmt.setString(2, n_content);
				pstmt.setInt(3, no);
				int cnt = pstmt.executeUpdate();
			} finally {
				JdbcUtil.close(pstmt);			
			}
		}
	//삭제
	   public int deleteNotice(Connection conn, int no) {
			
			String sql = "delete from notice " + 
						 "where N_NO=?";
			
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















