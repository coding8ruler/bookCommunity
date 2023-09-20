package qna.dao;

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
import qna.model.Qna;
import qna.model.dto.Writer;

public class QnaDAO {
	
	PreparedStatement pstmt = null; 
	Statement stmt = null;
	ResultSet rs = null;
	//목록
	public List<Qna> select(Connection conn,int startRow, int size) throws SQLException {
		String sql = 
				"select Q_NO,Q_TITLE,Q_CONTENT,Q_WRITERID,Q_WRITERNAME,M_NO,Q_CNT,REGDATE " + 
				"from qna " + 
				"order by Q_NO desc limit ?,?";
		
		List<Qna> qnaList = new ArrayList<Qna>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
					Qna qna = converQna(rs);
					qnaList.add(qna);
			}
			return qnaList;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			}
		}
	
	private Qna converQna(ResultSet rs) throws SQLException {
		
		return new Qna(	rs.getInt("q_no"),
						rs.getString("q_title"),
						rs.getString("q_content"),
			new Writer(	rs.getString("q_writerid"),
						rs.getString("q_writername"),
						rs.getInt("m_no")),
			   			rs.getInt("q_cnt"),
			   			toDate(rs.getTimestamp("regdate")));
	}
	
	private Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}
	
	
	//전체 게시글 수
	public int selectCount(Connection conn) throws SQLException  {
		String sql = "select count(Q_NO) "+
					 "from qna";
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
	//상세조회
	public Qna selectById(Connection conn, int no) throws SQLException {
		String sql = "select Q_NO,Q_TITLE,Q_CONTENT,Q_WRITERID,Q_WRITERNAME,M_NO,Q_CNT,REGDATE " + 
						"from qna " + 
						"where Q_NO=?";
		Qna qna = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				qna = converQna(rs);
			}
			return qna;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	//조회수증가
	public void increaseQ_cnt(Connection conn, int no) throws SQLException {
		String sql = "update qna "+ 
					 "set Q_CNT=Q_CNT+1 "+ 
					 "where Q_NO=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,no);
			int q_cnt = pstmt.executeUpdate();
			} finally {
			JdbcUtil.close(pstmt);			
			}
		}
	
	//글쓰기
		public Qna insert(Connection conn, Qna qna) throws SQLException {
			String sql = "insert into qna(Q_TITLE, Q_CONTENT, Q_WRITERID, Q_WRITERNAME, M_NO, Q_CNT, REGDATE ) "+ 
						  "values(?, ?, ?, ?, ?, 0, ?)";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, qna.getQ_title());
				pstmt.setString(2, qna.getQ_content());
				pstmt.setString(3, qna.getQ_writer().getQ_writerid());
				pstmt.setString(4, qna.getQ_writer().getQ_writername());
				pstmt.setInt(5, qna.getQ_writer().getM_no());
				pstmt.setTimestamp(6, toTimestamp(qna.getRegdate()));
				
				int cnt = pstmt.executeUpdate();
				
				if(cnt>0) {  
					stmt = conn.createStatement();
					rs = stmt.executeQuery("select last_insert_id() from qna");
					
					if(rs.next()) {  
						int newNum = rs.getInt(1);
						return new Qna ( 	newNum,   
											qna.getQ_title(),
											qna.getQ_content(),
											qna.getQ_writer(),
											0,
											qna.getRegdate());
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
		
	//글수정
	   public void update(Connection conn,String q_title, String q_content,int no) throws SQLException {
			String sql = "update qna " + 
						 "set Q_TITLE=?,Q_CONTENT=?,REGDATE=now() " + 
						 "where Q_NO=?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, q_title);
				pstmt.setString(2, q_content);
				pstmt.setInt(3, no);
				int cnt = pstmt.executeUpdate();
			} finally {
				JdbcUtil.close(pstmt);			
			}
		}
	   
	//글삭제
	   public int deleteQna(Connection conn, int no) {
			String sql = "delete from qna "+ 
						 "where Q_NO=?" ;
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
	

	 //댓글목록조회
 	//파라미터 int q_no : 글번호
 		public List<Qna> selectQnaCmtList(Connection conn,int q_no) {
 			PreparedStatement stmt = null;
 			List<Qna> qnaList = new ArrayList<Qna>();
 			ResultSet rs = null;
 			String sql = 
 					"select Q_CMT_NO,Q_NO,Q_CMT_WRITERNAME,REGDATE,Q_CMT_CONTENT " + 
 					"from qnacomment " + 
 					"where Q_NO=? " + 
 					"order by Q_NO desc";
 		try {
 			stmt = conn.prepareStatement(sql);
 			stmt.setInt(1, q_no);
 			rs = stmt.executeQuery();
 			while(rs.next()) {
 				Qna qna = new Qna();
 				qna.setQ_cmt_no(rs.getInt("Q_CMT_NO"));
 				qna.setQ_cmt_writername(rs.getString("Q_CMT_WRITERNAME"));
 				qna.setRegdate(rs.getTimestamp("REGDATE"));
 				qna.setQ_cmt_content(rs.getString("Q_CMT_CONTENT"));
 				
 				qna.setQ_no(rs.getInt("Q_NO"));
 				
 				qnaList.add(qna);//목록에 추가
 			}
 		}catch(SQLException e) {
 			e.printStackTrace();
 		}finally {
 			JdbcUtil.close(rs);
 		}
 			return qnaList;
 		}
 		
 		//댓글쓰기
 		//parameter : qna=>원글번호,작성자,작성일,내용
 		//return : int=>입력된 레코드수. 입력성공이면1, 실패라면 0리턴
 		public int writeQnaCmt(Connection conn,Qna qna)  throws SQLException {
 			PreparedStatement stmt = null;
 			String sql = "insert into qnacomment(Q_NO,Q_CMT_WRITERNAME,REGDATE,Q_CMT_CONTENT) " + 
 						 "values(?,?,?,?)";
 			int result = 0;//입력된 레코드수를 저장하기위한 변수선언 및 초기화
 			try {
 				stmt = conn.prepareStatement(sql);
 				stmt.setInt(1, qna.getQ_no());
 				stmt.setString(2,qna.getQ_cmt_writername());
 				stmt.setTimestamp(3, new Timestamp(qna.getRegdate().getTime()));//p593 48라인
 				stmt.setString(4,qna.getQ_cmt_content());
 				
 				result = stmt.executeUpdate();
 			}finally {
 				JdbcUtil.close(stmt);
 			}
 			return result;
 		}
 		
 		//댓글수정
 		public int updateQnaCmt(Connection conn,String q_cmt_content,int q_cmt_no){
 			PreparedStatement stmt = null;
 			String sql = "update qnacomment " + 
 				 		 "set Q_CMT_CONTENT=? "+ 
 				 		 "where Q_CMT_NO=?";
 			int result=0;
 			try {
 				stmt = conn.prepareStatement(sql);
 				stmt.setString(1, q_cmt_content);
 				stmt.setInt(2, q_cmt_no);
 				result = stmt.executeUpdate();//1이면 update된 record수가 1행이 있다
 			}catch (SQLException e) {
 			}finally {
 				JdbcUtil.close(stmt);
 			}
 			return result;
 		}
 		
 		//댓글삭제
 		   public int deleteQnaCmt(Connection conn, int q_cmt_no) {
 				System.out.println("dao deleteQnaCmt() q_cmt_no="+q_cmt_no);
 				String sql = "delete from qnacomment " +
 							 "where Q_CMT_NO=?";
 				
 				int result = 0; 
 				
 				try {
 					pstmt = conn.prepareStatement(sql);
 					pstmt.setInt(1, q_cmt_no);
 					result = pstmt.executeUpdate();
 					
 					return result;
 					
 				}catch (SQLException e) {
 					e.printStackTrace();
 					
 				}finally {
 					JdbcUtil.close(pstmt);
 				}
 				return result;
 			}




}















