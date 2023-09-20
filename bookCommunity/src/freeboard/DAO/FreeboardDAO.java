package freeboard.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import freeboard.model.Freeboard;
import freeboard.model.Fwriter;
import freeboard.model.FreplyDTO;
import jdbc.conn.JdbcUtil;

public class FreeboardDAO {
	
	PreparedStatement pstmt = null; 
	Statement stmt = null;
	ResultSet rs = null;
	//목록보기
	public List<Freeboard> select(Connection conn,int startRow, int size) throws SQLException {
		String sql = 
				"select F_NO,M_NO,WRITER_ID,WRITER_NAME,F_TITLE, " + 
				"F_CONTENT,REGDATE,MODDATE,F_CNT " + 
				"from  freeboard " +
				"order by F_NO desc limit ?,?";
		
		List<Freeboard> freeboardList = new ArrayList<Freeboard>();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, size);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				Freeboard freeboard = converFreeboard(rs);
				freeboardList.add(freeboard);
			}
			return freeboardList;
			}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			}
		}
	private Freeboard converFreeboard(ResultSet rs) throws SQLException {
		Freeboard f=new Freeboard(rs.getInt("F_NO"),
						  rs.getInt("M_NO"),
			   new Fwriter(rs.getString("WRITER_ID"), rs.getString("WRITER_NAME")),
						  rs.getString("F_TITLE"), 
						  rs.getString("F_CONTENT"),
						  toDate(rs.getTimestamp("REGDATE")), 
						  toDate(rs.getTimestamp("MODDATE")), 
						  rs.getInt("F_CNT"));
		System.out.println(f); 
		return f;
		 
	}
	private Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}
	public int selectCount(Connection conn) throws SQLException  {
		
		String sql = "select count(F_NO) " +
					 "from freeboard";
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
	//상세보기
	public Freeboard selectById(Connection conn, int no) throws SQLException {
		
		String sql = "select F_NO,M_NO,WRITER_ID,WRITER_NAME,F_TITLE,F_CONTENT,REGDATE,MODDATE,F_CNT " + 
					 "from freeboard " + 
					 "where F_NO=?";
		
		Freeboard freeboard = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				freeboard = converFreeboard(rs);
			}
			return freeboard;
			
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	//조회수
	public void increaseFcnt(Connection conn, int no) throws SQLException {
			
		String sql = "update freeboard " + 
					 "set F_CNT=F_CNT+1 " + 
					 "where F_NO=?";
			
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,no);
			int fCnt = pstmt.executeUpdate();
			System.out.println("fCnt= " +fCnt);	
			} finally {
			JdbcUtil.close(pstmt);			
			}
		}
	//글쓰기
		public Freeboard insert(Connection conn, Freeboard freeboard) throws SQLException {
			
			String sql = "insert into freeboard(M_NO,WRITER_ID,WRITER_NAME,F_TITLE,F_CONTENT,REGDATE,MODDATE,F_CNT)" + 
						  "values(1,?,?,?,?,?,?,0)";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, freeboard.getfWriter().getWriterId());
				pstmt.setString(2, freeboard.getfWriter().getWriterName());
				pstmt.setString(3, freeboard.getfTitle());
				pstmt.setString(4, freeboard.getfContent());
				pstmt.setTimestamp(5, toTimestamp(freeboard.getRegDate()));
				pstmt.setTimestamp(6, toTimestamp(freeboard.getModDate()));
				
				int cnt = pstmt.executeUpdate();
				System.out.println("insert cnt ="+cnt);
				
				if(cnt>0) {  
					stmt = conn.createStatement();
					rs = stmt.executeQuery("select last_insert_id() from freeboard");
					
					if(rs.next()) {  
						int newNum = rs.getInt(1);
						return new Freeboard ( newNum,   
											1,
											freeboard.getfWriter(), 
											freeboard.getfTitle(),
											freeboard.getfContent(),
											freeboard.getRegDate(), 
											freeboard.getModDate(), 
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
	   public void update(Connection conn,String fTitle, String fContent,int no) throws SQLException {
			
			String sql = "update freeboard " + 
						 "set F_TITLE=?,F_CONTENT=?,REGDATE=now() " + 
						 "where F_NO=?";
			
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, fTitle);
				pstmt.setString(2, fContent);
				pstmt.setInt(3, no);
				int cnt = pstmt.executeUpdate();
			} finally {
				JdbcUtil.close(pstmt);			
			}
		}
	//삭제
	   public int deleteFreeboard(Connection conn, int no) {
			
			String sql = "delete from freeboard " + 
						 "where F_NO=?";
			
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
		//파라미터 int fNo : 게시글번호
		public List<FreplyDTO> selectFreplyList(Connection conn,int rNo) {
			System.out.println("int fNo="+rNo);
			PreparedStatement stmt = null;
			List<FreplyDTO> freplyList = new ArrayList<FreplyDTO>();
			ResultSet rs = null;
			String sql = 
					"select fr_no,f_no,fr_writer_id,fr_writer_name,fr_title,fr_content,fr_regdate "+
					"from freply "+
					"where f_no=? "+
					"order by f_no asc";
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, rNo);
			rs = stmt.executeQuery();
			while(rs.next()) {
				FreplyDTO freplyDTO = new FreplyDTO();
				freplyDTO.setrNo(rs.getInt("fr_no"));
				freplyDTO.setWriterId(rs.getString("fr_writer_id"));
				freplyDTO.setWriterName(rs.getString("fr_writer_name"));
				freplyDTO.setrTitle(rs.getString("fr_title"));
				freplyDTO.setrContent(rs.getString("fr_content"));
				freplyDTO.setRegeDate(rs.getTimestamp("fr_regdate"));
				
				freplyDTO.setfNo(rs.getInt("f_no"));
				
				
				freplyList.add(freplyDTO);//목록에 추가
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
		}
			return freplyList;
		}
		
		//댓글쓰기
		//prameter : freplyDTO=>원글번호,작성자,제목,내용,작성일
		//return : int=>입력된 레코드수. 입력성공이면1, 실패라면 0리턴
		public int writeReply(Connection conn,FreplyDTO freplyDTO)  throws SQLException {
			PreparedStatement stmt = null;
			String sql = "insert into freply(f_no,fr_writer_id,fr_title,fr_content,fr_regdate) " + 
					"values(?,?,?,?,?)";
			int result = 0;//입력된 레코드수를 저장하기위한 변수선언 및 초기화
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, freplyDTO.getfNo());
				stmt.setString(2,freplyDTO.getWriterId());
				stmt.setString(3,freplyDTO.getrTitle());
				stmt.setString(4,freplyDTO.getrContent());
				stmt.setTimestamp(5,new Timestamp(freplyDTO.getRegeDate().getTime()));//p593 48라인
				
				result = stmt.executeUpdate();
				System.out.println(result);
				//result가 1이면 insert된 record수가 1행이 있다->입력성공
			}finally {
				JdbcUtil.close(stmt);
			}
			return result;
		}
		
		//댓글수정하기
		public int replyModify(Connection conn,String rContent,int rNo){
			PreparedStatement stmt = null;
			String sql = "update freply " + 
				 		 "set  fr_content=? "+ 
				 		 "where fr_no=?";
			int result=0;
			try {
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, rContent);
				stmt.setInt(2, rNo);
				result = stmt.executeUpdate();//1이면 update된 record수가 1행이 있다
			}catch (SQLException e) {
				System.out.println("댓글수정 관련 에러:"+e);
			}finally {
				JdbcUtil.close(stmt);
			}
			return result;
		}
		
		//댓글삭제
		   public int deleteFreply(Connection conn, int rNo) {
				
				String sql = "delete from freply " +
							 "where fr_no=?";
				
				int rcnt = 0; 
				
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, rNo);
					rcnt = pstmt.executeUpdate();
					
					return rcnt;
					
				}catch (SQLException e) {
					e.printStackTrace();
					
				}finally {
					JdbcUtil.close(pstmt);
				}
				return rcnt;
			}
		   
}















