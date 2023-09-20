package event.dao;

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
import event.model.Event;
import event.model.EWriter;

public class EventDAO {
	
	PreparedStatement pstmt = null; 
	Statement stmt = null;
	ResultSet rs = null;
	//Î™©Î°ù
	public List<Event> select(Connection conn,int startRow, int size) throws SQLException {
		String sql = 
				"select E_NO,M_NO,E_WRITERID,E_WRITERNAME,E_TITLE, " + 
				"E_CONTENT,REGDATE,E_CNT " + 
				"from  event " +
				"order by REGDATE desc limit ?,?";
		
		List<Event> eventList = new ArrayList<Event>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Event event = converEvent(rs);
				eventList.add(event);
			}
			return eventList;
			
			}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			}
		}
	private Event converEvent(ResultSet rs) throws SQLException {
		
		return  new Event( rs.getInt("E_NO"),
						  rs.getInt("M_NO"),
						  new EWriter(rs.getString("E_WRITERID"), rs.getString("E_WRITERNAME")),
						  rs.getString("E_TITLE"), 
						  rs.getString("E_CONTENT"),
						  toDate(rs.getTimestamp("REGDATE")), 
						  rs.getInt("E_CNT"));
		
	}
	private Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}
	public int selectCount(Connection conn) throws SQLException  {
		
		String sql = "select count(E_NO) " +
					 "from event";
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
	//?ÉÅ?Ñ∏Î≥¥Í∏∞
	public Event selectById(Connection conn, int no) throws SQLException {
		
		String sql = 
					"select E_NO,M_NO,E_WRITERID,E_WRITERNAME,E_TITLE,E_CONTENT,REGDATE,E_CNT " + 
					 "from event " + 
					 "where E_NO=?";
		
		Event event = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				event = converEvent(rs);
			}
			return event;
			
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	//Ï°∞Ìöå?àò
	public void increaseE_cnt(Connection conn, int no) throws SQLException {
			
		String sql = "update event " + 
					 "set E_CNT=E_CNT+1 " + 
					 "where E_NO=?";
			
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,no);
			int e_cnt = pstmt.executeUpdate();
			System.out.println("ÔøΩÔøΩ»∏ÔøΩÔøΩ ÔøΩÔøΩÔøΩÔøΩÔøΩÔøΩ rowÔøΩÔøΩ = " + e_cnt);	
			} finally {
			JdbcUtil.close(pstmt);			
			}
		}
	//Í∏??ì∞Í∏?
		public Event insert(Connection conn, Event event) throws SQLException {
			
			String sql = "insert into event(M_NO,E_WRITERID,E_WRITERNAME,E_TITLE,E_CONTENT,REGDATE,E_CNT)" + 
					  "values(9999,?,?,?,?,?,0)";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, event.getE_writer().getE_writerid());
				pstmt.setString(2, event.getE_writer().getE_writerid());
				pstmt.setString(3, event.getE_title());
				pstmt.setString(4, event.getE_content());
				pstmt.setTimestamp(5, toTimestamp(event.getRegdate()));
				
				int cnt = pstmt.executeUpdate();
				System.out.println("insertÔøΩÔøΩÔø? ÔøΩÔøΩ ÔøΩÔøΩÔøΩÔøΩ ="+cnt);
				
				if(cnt>0) {  
					stmt = conn.createStatement();
					rs = stmt.executeQuery("select last_insert_id() from event");
					
					if(rs.next()) {  
						int newNum = rs.getInt(1);
						return new Event ( newNum,   
											9999,
											event.getE_writer(), 
											event.getE_title(),
											event.getE_content(),
											event.getRegdate(), 
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
	//?àò?†ï
	   public void update(Connection conn,String e_title, String e_content,int no) throws SQLException {
			
			String sql = "update event " + 
						 "set E_TITLE=?,E_CONTENT=?,REGDATE=now() " + 
						 "where E_NO=?";
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, e_title);
				pstmt.setString(2, e_content);
				pstmt.setInt(3, no);
				int cnt = pstmt.executeUpdate();
			} finally {
				JdbcUtil.close(pstmt);			
			}
		}
	//?Ç≠?†ú
	   public int deleteEvent(Connection conn, int no) {
			
			String sql = "delete from event " + 
						 "where E_NO=?";
			
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















